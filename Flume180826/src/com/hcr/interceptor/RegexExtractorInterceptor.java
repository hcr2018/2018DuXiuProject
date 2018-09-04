package com.hcr.interceptor;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.Charsets;
import org.apache.commons.lang.StringUtils;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.flume.interceptor.RegexExtractorInterceptorPassThroughSerializer;
import org.apache.flume.interceptor.RegexExtractorInterceptorSerializer;

public class RegexExtractorInterceptor implements Interceptor {
	static final String REGEX="regex";
	static final String SERIALIZERS="serializes";
	private static final Logger logger = LoggerFactory.getLogger(org.apache.flume.interceptor.RegexExtractorInterceptor.class);
	private final Pattern regex;
	
	 private final List<RegexExtractorInterceptor.NameAndSerializer> serializers;
	    private static Map<String, String> values = new HashMap<String, String>();
	
	    private RegexExtractorInterceptor(Pattern regex, List<RegexExtractorInterceptor.NameAndSerializer> serializers) {
	        this.regex = regex;
	        this.serializers = serializers;
	    }
	
	    
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 拦截器
	 * 当正则表达式匹配上Event的时候，使用RegexExtractorInterceptor类的一个静态属性记录下来这个Event的头部信息（也就是诸如2016-07-08/ERROR的匹配到的信息），
	 * 然后当下一条记录不匹配的时候，显然，这行日志是属于上一条记录的
	 */
	@Override
	public Event intercept(Event event) {
		// TODO Auto-generated method stub
		Matcher matcher = this.regex.matcher(new String(event.getBody(), Charsets.UTF_8));
		Map<String, String> headers = event.getHeaders();
		if(matcher.find()){
			int group=0;
			 for (int count = matcher.groupCount(); group < count; ++group) {
	                int groupIndex = group + 1;
	                if (groupIndex > this.serializers.size()) {
	                    if (logger.isDebugEnabled()) {
	                        logger.debug("Skipping group {} to {} due to missing serializer", Integer.valueOf(group), Integer.valueOf(count));
	                    }
	                    break;
	                }

	                NameAndSerializer serializer = (RegexExtractorInterceptor.NameAndSerializer) this.serializers.get(group);
	                if (logger.isDebugEnabled()) {
	                    logger.debug("Serializing {} using {}", serializer.headerName, serializer.serializer);
	                }

	                headers.put(serializer.headerName, serializer.serializer.serialize(matcher.group(groupIndex)));
	                values.put(serializer.headerName, serializer.serializer.serialize(matcher.group(groupIndex)));
	            }
	        } else {
	            //添加的代码
	            for (Map.Entry<String, String> en : values.entrySet()) {
	                headers.put(en.getKey(), en.getValue());
	            }
	        }
	        return event;
	}

	@Override
	public List<Event> intercept(List<Event> events) {
		// TODO Auto-generated method stub
		 List<Event> intercepted = Lists.newArrayListWithCapacity(events.size());
	        Iterator i$ = events.iterator();

	        while (i$.hasNext()) {
	            Event event = (Event) i$.next();
	            Event interceptedEvent = this.intercept(event);
	            if (interceptedEvent != null) {
	                intercepted.add(interceptedEvent);
	            }
	        }

	        return intercepted;
	}
	
	 static class NameAndSerializer {
	        private final String headerName;
	        private final RegexExtractorInterceptorSerializer serializer;

	        public NameAndSerializer(String headerName, RegexExtractorInterceptorSerializer serializer) {
	            this.headerName = headerName;
	            this.serializer = serializer;
	        }
	    }
	 
	 
	 public static class Builder implements org.apache.flume.interceptor.Interceptor.Builder {
	        private Pattern regex;
	        private List<RegexExtractorInterceptor.NameAndSerializer> serializerList;
	        private final RegexExtractorInterceptorSerializer defaultSerializer = new RegexExtractorInterceptorPassThroughSerializer();

	        public Builder() {
	        }

			@Override
			public void configure(Context context) {
				// TODO Auto-generated method stub
				String regexString = context.getString("regex");
				 Preconditions.checkArgument(!StringUtils.isEmpty(regexString), "Must supply a valid regex string");
		            this.regex = Pattern.compile(regexString);
		            this.regex.pattern();
		            this.regex.matcher("").groupCount();
		            this.configureSerializers(context);
			}

			@Override
			public Interceptor build() {
				   Preconditions.checkArgument(this.regex != null, "Regex pattern was misconfigured");
		            Preconditions.checkArgument(this.serializerList.size() > 0, "Must supply a valid group match id list");
		            return new RegexExtractorInterceptor(this.regex, this.serializerList);
			}
	 
			 private void configureSerializers(Context context) {
		            String serializerListStr = context.getString("serializers");
		            Preconditions.checkArgument(!StringUtils.isEmpty(serializerListStr), "Must supply at least one name and serializer");
		            String[] serializerNames = serializerListStr.split("\\s+");
		            Context serializerContexts = new Context(context.getSubProperties("serializers."));
		            this.serializerList = Lists.newArrayListWithCapacity(serializerNames.length);
		            String[] arr$ = serializerNames;
		            int len$ = serializerNames.length;

		            for (int i$ = 0; i$ < len$; ++i$) {
		                String serializerName = arr$[i$];
		                Context serializerContext = new Context(serializerContexts.getSubProperties(serializerName + "."));
		                String type = serializerContext.getString("type", "DEFAULT");
		                String name = serializerContext.getString("name");
		                Preconditions.checkArgument(!StringUtils.isEmpty(name), "Supplied name cannot be empty.");
		                if ("DEFAULT".equals(type)) {
		                    this.serializerList.add(new RegexExtractorInterceptor.NameAndSerializer(name, this.defaultSerializer));
		                } else {
		                    this.serializerList.add(new RegexExtractorInterceptor.NameAndSerializer(name, this.getCustomSerializer(type, serializerContext)));
		                }
		            }

		        }
			 
			 private RegexExtractorInterceptorSerializer getCustomSerializer(String clazzName, Context context) {
		            try {
		                RegexExtractorInterceptorSerializer serializer = (RegexExtractorInterceptorSerializer) Class.forName(clazzName).newInstance();
		                serializer.configure(context);
		                return serializer;
		            } catch (Exception var4) {
		                RegexExtractorInterceptor.logger.error("Could not instantiate event serializer.", var4);
		                Throwables.propagate(var4);
		                return this.defaultSerializer;
		            }
		        }
	 }
	 
}

