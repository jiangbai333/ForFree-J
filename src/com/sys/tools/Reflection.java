package com.sys.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("hiding")
public class Reflection<T> {
	
	/** ʵ�����󻺴� */
	@SuppressWarnings("unused")
	private T cls = null;
	
	private Class<T> primal = null;
	
	public Reflection () {}
	
	/**
	 * ���ݹ��췽���������ʵ��
	 * 
	 *	package com.sys.tools;
	 *	public class F {
	 *
	 *		public F(String str) {
	 *
	 *		}
	 *	}
	 *
	 *	new Reflection<F>("com.sys.tools.F", new Class[] {
	 *		String.class
	 *	}, new Object[] {
	 *		"���ݸ� F ���캯�����ַ�������"
	 *	}).getCls();
	 *
	 * @param cls ��Ҫ��ʵ�����������·��
	 * @param ctor ���췽������
	 * @param obj ���췽����Ҫ���յĲ���
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public Reflection (String cls, Class[] ctor, Object[] obj) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, ClassNotFoundException{
		
		Class<T> c = (Class<T>) Class.forName(cls);
		Constructor cons = c.getConstructor(ctor);
		
		this.primal = c;
		this.cls = (T) cons.newInstance(obj);
	}
	
	/**
	 * ���ݹ��췽���������ʵ��, �Զ��жϹ��췽����������
	 * 
	 *	package com.sys.tools;
	 *	public class F {
	 *
	 *		public F(String str) {
	 *
	 *		}
	 *	}
	 *
	 *	new Reflection<F>("com.sys.tools.F", new Object[] {
	 *		"���ݸ� F ���캯�����ַ�������"
	 *	}).getCls();
	 *
	 * @param cls ��Ҫ��ʵ�����������·��
	 * @param obj ���췽����Ҫ���յĲ���
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public Reflection (String cls, Object[] obj) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, ClassNotFoundException{
		
		Class[] ctor = new Class[obj.length];
		for (int i = 0; i < obj.length; i++) {
			ctor[i] = obj[i].getClass();
		}
		
		Class<T> c = (Class<T>) Class.forName(cls);
		Constructor cons = c.getConstructor(ctor);
		
		this.primal = c;
		this.cls = (T) cons.newInstance(obj);
	}
	
	/**
	 * �����޲������췽�� �������ʵ��
	 * @param cls
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public Reflection (String cls) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		Class<T> c = (Class<T>) Class.forName(cls);
		
		this.primal = c;
		this.cls = c.newInstance();
	}

	/**
	 * ����ʵ���в�������
	 * 
	 *	package com.sys.tools;
	 *	public class F {
	 *		public void test(int a){}
	 *	}
	 * 
	 * 	new Reflection<F>("com.sys.tools.F").todo("test", new Class[] {int.class}, new Object[]{100});
	 * 
	 * @param md ��Ҫ�����õķ�����
	 * @param param �������βνṹ
	 * @param obj ����ʵ��
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public Object todo(String md, Class[] param, Object[] obj) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		return this.primal.getMethod(md, param).invoke(this.cls, obj);
	}

	@SuppressWarnings("unchecked")
	public Object todo(String md, Object[] obj) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		Class[] param = new Class[obj.length];
		for (int i = 0; i < obj.length; i++) {
			param[i] = obj[i].getClass();
		}
		
		return this.primal.getMethod(md, param).invoke(this.cls, obj);
	}
	
	/**
	 * ����ʵ���޲�������
	 * 
	 *	package com.sys.tools;
	 *	public class F {
	 *		public void test(){}
	 *	}
	 *
	 *	new Reflection<F>("com.sys.tools.F").todo("test");
	 *
	 * @param todo ��Ҫ�����õķ�����
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("unchecked")
	public Object todo(String todo) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		return this.primal.getMethod(todo).invoke(this.cls);
	}
	
	public Object todo(T cls, String todo) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return cls.getClass().getMethod(todo).invoke(cls);
	}
	
	public T getCls() {
		return cls;
	}
	
//	public static void main(String[] args) throws SecurityException, InvocationTargetException, NoSuchMethodException {
//			
//		try {
//			new Reflection<F>("com.sys.tools.F", new Object[]{"asdasdasd", new Template()});
////			new Reflection<F>("com.sys.tools.F").todo("test", new Class[] {int.class}, new Object[]{100});
////
////			System.out.println("�в��з���test�����ã�����ֵ��" + new Reflection<F>("com.sys.tools.F").todo("test", new Class[] {String.class}, new Object[]{"asdasdasdasd"}));
////		
////
////			F f = (F) new Reflection<F>("com.sys.tools.F").todo("test1");
////		
//			
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
