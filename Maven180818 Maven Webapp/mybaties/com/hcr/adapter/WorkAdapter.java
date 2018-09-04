package com.hcr.adapter;

/**
 *   ≈‰∆˜ƒ£ Ω
 * @author cjx
 *
 */
public class WorkAdapter implements IworkAdapter {
	
	public void work(Object worker) {
		// TODO Auto-generated method stub
		if (worker instanceof IProgranmer) {
			System.out.println(((IProgranmer) worker).program());
			
		}
		
		if (worker instanceof ICooker) {
			System.out.println(((ICooker) worker).cook());
			
		}

	}

	public boolean supports(Object worker) {
		// TODO Auto-generated method stub
		return false;
	}

}
