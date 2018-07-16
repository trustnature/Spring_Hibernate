package xyz.tuny.jx.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHelper implements ApplicationContextAware {
	private static ApplicationContext appCtx;  
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		 appCtx = applicationContext;
	}
	
	public static Object getBean( String beanName ) {  
		
        return appCtx.getBean( beanName );  
    }  

}
