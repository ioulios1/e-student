/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axicik;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author ioulios
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.axicik.AddStudentResource.class);
        resources.add(com.axicik.AddUserResource.class);
        resources.add(com.axicik.GetLessonResource.class);
        resources.add(com.axicik.GetLessonsResource.class);
        resources.add(com.axicik.GetServicesResource.class);
        resources.add(com.axicik.GetStudentInfoResource.class);
        resources.add(com.axicik.LoginResource.class);
        resources.add(com.axicik.LogoutResource.class);
        resources.add(com.axicik.ModifyGrades.class);
        resources.add(com.axicik.PutclassesResource.class);
        resources.add(com.axicik.RolesResources.class);
    }
    
}
