/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package annotation;

/**
 * @author fenghao.xing
 * @version : NoBug.java, v 0.1 2018-04-03 15:48 fenhao.xing Exp $
 */
public class NoBug {

    @JianChaAnnotation
    public void suanShu(){
        System.out.println("1234567890");
    }
    @JianChaAnnotation
    public void jiafa(){
        System.out.println("1+1="+1+1);
    }
    @JianChaAnnotation
    public void jiefa(){
        System.out.println("1-1="+(1-1));
    }
    @JianChaAnnotation
    public void chengfa(){
        System.out.println("3 x 5="+ 3*5);
    }
    @JianChaAnnotation
    public void chufa(){
        System.out.println("6 / 0="+ 6 / 0);
    }

    public void ziwojieshao(){
        System.out.println("我写的程序没有 bug!");
    }


}
