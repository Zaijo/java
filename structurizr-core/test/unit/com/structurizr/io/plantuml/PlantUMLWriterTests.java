package com.structurizr.io.plantuml;

import com.structurizr.Workspace;
import com.structurizr.model.*;
import com.structurizr.view.*;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class PlantUMLWriterTests {

    private PlantUMLWriter plantUMLWriter;
    private Workspace workspace;
    private StringWriter stringWriter;

    @Before
    public void setUp() {
        plantUMLWriter = new PlantUMLWriter();
        workspace = new Workspace("Name", "Description");
        stringWriter = new StringWriter();
    }

    @Test
    public void test_writeWorkspace_DoesNotThrowAnExceptionWhenPassedNullParameters() throws Exception {
        plantUMLWriter.write((Workspace) null, null);
        plantUMLWriter.write(workspace, null);
        plantUMLWriter.write((Workspace) null, stringWriter);
    }

    @Test
    public void test_writeView_DoesNotThrowAnExceptionWhenPassedNullParameters() throws Exception {
        populateWorkspace();

        plantUMLWriter.write((View) null, null);
        plantUMLWriter.write(workspace.getViews().getEnterpriseContextViews().stream().findFirst().get(), null);
        plantUMLWriter.write((View) null, stringWriter);
    }

    @Test
    public void test_writeWorkspace() throws Exception {
        populateWorkspace();

        plantUMLWriter.write(workspace, stringWriter);
        assertEquals("@startuml" + System.lineSeparator() +
                "title Enterprise Context for Some Enterprise" + System.lineSeparator() +
                "component \"E-mail System\" <<Software System>> as 4" + System.lineSeparator() +
                "package SomeEnterprise {" + System.lineSeparator() +
                "  actor \"User\" <<Person>> as 1" + System.lineSeparator() +
                "  component \"Software System\" <<Software System>> as 2" + System.lineSeparator() +
                "}" + System.lineSeparator() +
                "4 ..> 1 : Delivers e-mails to" + System.lineSeparator() +
                "2 ..> 4 : Sends e-mail using" + System.lineSeparator() +
                "1 ..> 2 : Uses" + System.lineSeparator() +
                "@enduml" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "@startuml" + System.lineSeparator() +
                "title Software System - System Context" + System.lineSeparator() +
                "component \"E-mail System\" <<Software System>> as 4" + System.lineSeparator() +
                "component \"Software System\" <<Software System>> as 2" + System.lineSeparator() +
                "actor \"User\" <<Person>> as 1" + System.lineSeparator() +
                "4 ..> 1 : Delivers e-mails to" + System.lineSeparator() +
                "2 ..> 4 : Sends e-mail using" + System.lineSeparator() +
                "1 ..> 2 : Uses" + System.lineSeparator() +
                "@enduml" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "@startuml" + System.lineSeparator() +
                "title Software System - Containers" + System.lineSeparator() +
                "component \"E-mail System\" <<Software System>> as 4" + System.lineSeparator() +
                "actor \"User\" <<Person>> as 1" + System.lineSeparator() +
                "package SoftwareSystem {" + System.lineSeparator() +
                "  component \"Database\" <<Container>> as 8" + System.lineSeparator() +
                "  component \"Web Application\" <<Container>> as 7" + System.lineSeparator() +
                "}" + System.lineSeparator() +
                "4 ..> 1 : Delivers e-mails to" + System.lineSeparator() +
                "1 ..> 7 : Uses <<HTTP>>" + System.lineSeparator() +
                "7 ..> 8 : Reads from and writes to <<JDBC>>" + System.lineSeparator() +
                "7 ..> 4 : Sends e-mail using" + System.lineSeparator() +
                "@enduml" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "@startuml" + System.lineSeparator() +
                "title Software System - Web Application - Components" + System.lineSeparator() +
                "component \"Database\" <<Container>> as 8" + System.lineSeparator() +
                "component \"E-mail System\" <<Software System>> as 4" + System.lineSeparator() +
                "actor \"User\" <<Person>> as 1" + System.lineSeparator() +
                "package WebApplication {" + System.lineSeparator() +
                "  component \"EmailComponent\" <<Component>> as 13" + System.lineSeparator() +
                "  component \"SomeController\" <<Spring MVC Controller>> as 12" + System.lineSeparator() +
                "  component \"SomeRepository\" <<Spring Data>> as 14" + System.lineSeparator() +
                "}" + System.lineSeparator() +
                "4 ..> 1 : Delivers e-mails to" + System.lineSeparator() +
                "13 ..> 4 : Sends e-mails using <<SMTP>>" + System.lineSeparator() +
                "12 ..> 13 : Sends e-mail using" + System.lineSeparator() +
                "12 ..> 14 : Uses" + System.lineSeparator() +
                "14 ..> 8 : Reads from and writes to <<JDBC>>" + System.lineSeparator() +
                "1 ..> 12 : Uses <<HTTP>>" + System.lineSeparator() +
                "@enduml" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "@startuml" + System.lineSeparator() +
                "title Web Application - Dynamic" + System.lineSeparator() +
                "component \"Database\" <<Container>> as 8" + System.lineSeparator() +
                "component \"SomeController\" <<Spring MVC Controller>> as 12" + System.lineSeparator() +
                "component \"SomeRepository\" <<Spring Data>> as 14" + System.lineSeparator() +
                "actor \"User\" <<Person>> as 1" + System.lineSeparator() +
                "1 -> 12 : Requests /something" + System.lineSeparator() +
                "12 -> 14 : Uses" + System.lineSeparator() +
                "14 -> 8 : select * from something" + System.lineSeparator() +
                "@enduml" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "@startuml" + System.lineSeparator() +
                "title Software System - Deployment" + System.lineSeparator() +
                "node \"Database Server\" <<Ubuntu 12.04 LTS>> as 23 {" + System.lineSeparator() +
                "  node \"MySQL\" <<MySQL 5.5.x>> as 24 {" + System.lineSeparator() +
                "    artifact \"Database\" <<Container>> as 25" + System.lineSeparator() +
                "  }" + System.lineSeparator() +
                "}" + System.lineSeparator() +
                "node \"Web Server\" <<Ubuntu 12.04 LTS>> as 20 {" + System.lineSeparator() +
                "  node \"Apache Tomcat\" <<Apache Tomcat 8.x>> as 21 {" + System.lineSeparator() +
                "    artifact \"Web Application\" <<Container>> as 22" + System.lineSeparator() +
                "  }" + System.lineSeparator() +
                "}" + System.lineSeparator() +
                "22 ..> 25 : Reads from and writes to <<JDBC>>" + System.lineSeparator() +
                "@enduml" + System.lineSeparator() +
                System.lineSeparator(), stringWriter.toString());
    }

    @Test
    public void test_writeEnterpriseContextView() throws Exception {
        populateWorkspace();

        EnterpriseContextView enterpriseContextView = workspace.getViews().getEnterpriseContextViews()
            .stream().findFirst().get();
        plantUMLWriter.write(enterpriseContextView, stringWriter);

        assertEquals("@startuml" + System.lineSeparator() +
                "title Enterprise Context for Some Enterprise" + System.lineSeparator() +
                "component \"E-mail System\" <<Software System>> as 4" + System.lineSeparator() +
                "package SomeEnterprise {" + System.lineSeparator() +
                "  actor \"User\" <<Person>> as 1" + System.lineSeparator() +
                "  component \"Software System\" <<Software System>> as 2" + System.lineSeparator() +
                "}" + System.lineSeparator() +
                "4 ..> 1 : Delivers e-mails to" + System.lineSeparator() +
                "2 ..> 4 : Sends e-mail using" + System.lineSeparator() +
                "1 ..> 2 : Uses" + System.lineSeparator() +
                "@enduml" + System.lineSeparator() +
                System.lineSeparator(), stringWriter.toString());

    }

    @Test
    public void test_writeSystemContextView() throws Exception {
        populateWorkspace();

        SystemContextView systemContextView = workspace.getViews().getSystemContextViews()
            .stream().findFirst().get();
        plantUMLWriter.write(systemContextView, stringWriter);

        assertEquals("@startuml" + System.lineSeparator() +
                "title Software System - System Context" + System.lineSeparator() +
                "component \"E-mail System\" <<Software System>> as 4" + System.lineSeparator() +
                "component \"Software System\" <<Software System>> as 2" + System.lineSeparator() +
                "actor \"User\" <<Person>> as 1" + System.lineSeparator() +
                "4 ..> 1 : Delivers e-mails to" + System.lineSeparator() +
                "2 ..> 4 : Sends e-mail using" + System.lineSeparator() +
                "1 ..> 2 : Uses" + System.lineSeparator() +
                "@enduml" + System.lineSeparator() +
                System.lineSeparator(), stringWriter.toString());
    }

    @Test
    public void test_writeContainerView() throws Exception {
        populateWorkspace();

        ContainerView containerView = workspace.getViews().getContainerViews()
            .stream().findFirst().get();
        plantUMLWriter.write(containerView, stringWriter);

        assertEquals("@startuml" + System.lineSeparator() +
                "title Software System - Containers" + System.lineSeparator() +
                "component \"E-mail System\" <<Software System>> as 4" + System.lineSeparator() +
                "actor \"User\" <<Person>> as 1" + System.lineSeparator() +
                "package SoftwareSystem {" + System.lineSeparator() +
                "  component \"Database\" <<Container>> as 8" + System.lineSeparator() +
                "  component \"Web Application\" <<Container>> as 7" + System.lineSeparator() +
                "}" + System.lineSeparator() +
                "4 ..> 1 : Delivers e-mails to" + System.lineSeparator() +
                "1 ..> 7 : Uses <<HTTP>>" + System.lineSeparator() +
                "7 ..> 8 : Reads from and writes to <<JDBC>>" + System.lineSeparator() +
                "7 ..> 4 : Sends e-mail using" + System.lineSeparator() +
                "@enduml" + System.lineSeparator() +
                System.lineSeparator(), stringWriter.toString());
    }

    @Test
    public void test_writeComponentsView() throws Exception {
        populateWorkspace();

        ComponentView componentView = workspace.getViews().getComponentViews()
            .stream().findFirst().get();
        plantUMLWriter.write(componentView, stringWriter);

        assertEquals("@startuml" + System.lineSeparator() +
                "title Software System - Web Application - Components" + System.lineSeparator() +
                "component \"Database\" <<Container>> as 8" + System.lineSeparator() +
                "component \"E-mail System\" <<Software System>> as 4" + System.lineSeparator() +
                "actor \"User\" <<Person>> as 1" + System.lineSeparator() +
                "package WebApplication {" + System.lineSeparator() +
                "  component \"EmailComponent\" <<Component>> as 13" + System.lineSeparator() +
                "  component \"SomeController\" <<Spring MVC Controller>> as 12" + System.lineSeparator() +
                "  component \"SomeRepository\" <<Spring Data>> as 14" + System.lineSeparator() +
                "}" + System.lineSeparator() +
                "4 ..> 1 : Delivers e-mails to" + System.lineSeparator() +
                "13 ..> 4 : Sends e-mails using <<SMTP>>" + System.lineSeparator() +
                "12 ..> 13 : Sends e-mail using" + System.lineSeparator() +
                "12 ..> 14 : Uses" + System.lineSeparator() +
                "14 ..> 8 : Reads from and writes to <<JDBC>>" + System.lineSeparator() +
                "1 ..> 12 : Uses <<HTTP>>" + System.lineSeparator() +
                "@enduml" + System.lineSeparator() +
                System.lineSeparator(), stringWriter.toString());
    }

    @Test
    public void test_writeDynamicView() throws Exception {
        populateWorkspace();

        DynamicView dynamicView = workspace.getViews().getDynamicViews()
                .stream().findFirst().get();
        plantUMLWriter.write(dynamicView, stringWriter);

        assertEquals("@startuml" + System.lineSeparator() +
                "title Web Application - Dynamic" + System.lineSeparator() +
                "component \"Database\" <<Container>> as 8" + System.lineSeparator() +
                "component \"SomeController\" <<Spring MVC Controller>> as 12" + System.lineSeparator() +
                "component \"SomeRepository\" <<Spring Data>> as 14" + System.lineSeparator() +
                "actor \"User\" <<Person>> as 1" + System.lineSeparator() +
                "1 -> 12 : Requests /something" + System.lineSeparator() +
                "12 -> 14 : Uses" + System.lineSeparator() +
                "14 -> 8 : select * from something" + System.lineSeparator() +
                "@enduml" + System.lineSeparator() +
                System.lineSeparator(), stringWriter.toString());
    }

    @Test
    public void test_writeDeploymentView() throws Exception {
        populateWorkspace();

        DeploymentView deploymentView = workspace.getViews().getDeploymentViews()
                .stream().findFirst().get();
        plantUMLWriter.write(deploymentView, stringWriter);

        assertEquals("@startuml" + System.lineSeparator() +
                "title Software System - Deployment" + System.lineSeparator() +
                "node \"Database Server\" <<Ubuntu 12.04 LTS>> as 23 {" + System.lineSeparator() +
                "  node \"MySQL\" <<MySQL 5.5.x>> as 24 {" + System.lineSeparator() +
                "    artifact \"Database\" <<Container>> as 25" + System.lineSeparator() +
                "  }" + System.lineSeparator() +
                "}" + System.lineSeparator() +
                "node \"Web Server\" <<Ubuntu 12.04 LTS>> as 20 {" + System.lineSeparator() +
                "  node \"Apache Tomcat\" <<Apache Tomcat 8.x>> as 21 {" + System.lineSeparator() +
                "    artifact \"Web Application\" <<Container>> as 22" + System.lineSeparator() +
                "  }" + System.lineSeparator() +
                "}" + System.lineSeparator() +
                "22 ..> 25 : Reads from and writes to <<JDBC>>" + System.lineSeparator() +
                "@enduml" + System.lineSeparator() +
                System.lineSeparator(), stringWriter.toString());
    }

    private void populateWorkspace() {
        Model model = workspace.getModel();
        model.setEnterprise(new Enterprise("Some Enterprise"));

        Person user = model.addPerson(Location.Internal, "User", "");
        SoftwareSystem softwareSystem = model.addSoftwareSystem(Location.Internal, "Software System", "");
        user.uses(softwareSystem, "Uses");

        SoftwareSystem emailSystem = model.addSoftwareSystem(Location.External, "E-mail System", "");
        softwareSystem.uses(emailSystem, "Sends e-mail using");
        emailSystem.delivers(user, "Delivers e-mails to");

        Container webApplication = softwareSystem.addContainer("Web Application", "", "");
        Container database = softwareSystem.addContainer("Database", "", "");
        user.uses(webApplication, "Uses", "HTTP");
        webApplication.uses(database, "Reads from and writes to", "JDBC");
        webApplication.uses(emailSystem, "Sends e-mail using");

        Component controller = webApplication.addComponent("SomeController", "", "Spring MVC Controller");
        Component emailComponent = webApplication.addComponent("EmailComponent", "");
        Component repository = webApplication.addComponent("SomeRepository", "", "Spring Data");
        user.uses(controller, "Uses", "HTTP");
        controller.uses(repository, "Uses");
        controller.uses(emailComponent, "Sends e-mail using");
        repository.uses(database, "Reads from and writes to", "JDBC");
        emailComponent.uses(emailSystem, "Sends e-mails using", "SMTP");

        DeploymentNode webServer = model.addDeploymentNode("Web Server", "A server hosted at AWS EC2.", "Ubuntu 12.04 LTS");
        webServer.addDeploymentNode("Apache Tomcat", "The live web server", "Apache Tomcat 8.x")
                .add(webApplication);
        DeploymentNode databaseServer = model.addDeploymentNode("Database Server", "A server hosted at AWS EC2.", "Ubuntu 12.04 LTS");
        databaseServer.addDeploymentNode("MySQL", "The live database server", "MySQL 5.5.x")
                .add(database);

        EnterpriseContextView
            enterpriseContextView = workspace.getViews().createEnterpriseContextView("enterpriseContext", "");
        enterpriseContextView.addAllElements();

        SystemContextView
            systemContextView = workspace.getViews().createSystemContextView(softwareSystem, "systemContext", "");
        systemContextView.addAllElements();

        ContainerView containerView = workspace.getViews().createContainerView(softwareSystem, "containers", "");
        containerView.addAllElements();

        ComponentView componentView = workspace.getViews().createComponentView(webApplication, "components", "");
        componentView.addAllElements();

        DynamicView dynamicView = workspace.getViews().createDynamicView(webApplication, "dynamic", "");
        dynamicView.add(user, "Requests /something", controller);
        dynamicView.add(controller, repository);
        dynamicView.add(repository, "select * from something", database);

        DeploymentView deploymentView = workspace.getViews().createDeploymentView(softwareSystem, "deployment", "");
        deploymentView.addAllDeploymentNodes();
    }

}
