/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.Serializable;

/**
 *
 * @author pavel
 */
public class People  implements Serializable{

    private String name;
    private String tell;
    private String skype;

    public People() {
    }

    public People(String name, String tell, String skype) {
        this.name = name;
        this.tell = tell;
        this.skype = skype;
    }

    public String getTell() {
        return tell;
    }
    public void setTell(String tell) {
        this.tell = tell;
    }
    
    public String getSkype() {
        return skype;
    }
    
    public void setSkype(String skype) {
        this.skype = skype;
    }

 

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
