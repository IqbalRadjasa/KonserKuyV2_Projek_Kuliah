/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@ManagedBean
@SessionScoped
public class SessionBean {

    public void checkSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        
        if (session != null && session.getAttribute("user") != null) {
            try {
                facesContext.getExternalContext().redirect("protected/home.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

