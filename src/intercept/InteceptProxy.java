package intercept;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;

public class InteceptProxy implements InvocationHandler{
	
	private Logger log = Logger.getLogger(InteceptProxy.class);
	
	private Intecepter intcept ;
	
	public InteceptProxy() {
	}
	
	public InteceptProxy(Intecepter intcept) {
		this.intcept = intcept;
	}
	
	public Intecepter getIntcept() {
		return intcept;
	}

	public void setIntcept(Intecepter intcept) {
		this.intcept = intcept;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		log.debug("before intcept:"+method.getName());
		Object obj = method.invoke(intcept, args);
		log.debug("after intcept:"+method.getName());
		return obj;
	}
	
	public Intecepter newInstance() {
		return (Intecepter)Proxy.newProxyInstance(InteceptProxy.class.getClassLoader(), new Class[]{Intecepter.class},this);
	}
	
	

}
