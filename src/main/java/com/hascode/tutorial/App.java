package com.hascode.tutorial;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {
    private Server jettyServer;

    public App() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        jettyServer = new Server(9000);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
                UserResource.class.getCanonicalName());
        jerseyServlet.setInitOrder(0);
    }

    public void start() throws Exception {
        jettyServer.start();
    }

    public void stop() throws Exception {
        try {
            jettyServer.stop();
        } finally {
            jettyServer.destroy();
        }
    }

    public static void main(String[] args) throws Exception {
        new App().run();
    }

    private void run() throws Exception {
        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }

}
