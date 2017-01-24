package com.foresee.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.logging.SLF4JLogDelegateFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * 
 * @author Abdel Dridi
 *
 *         Foresee Inc Copyright 2017
 *
 */
public class AppMain extends AbstractVerticle {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppMain.class);

	private static final Integer DEFAULT_HTTP_PORT = 8080;

	public static void main(String[] args) {
		System.setProperty(LoggerFactory.LOGGER_DELEGATE_FACTORY_CLASS_NAME, SLF4JLogDelegateFactory.class.getName());
		ProcessInit.run(AppMain.class);
	}

	@Override
	public void start(Future<Void> future) {

		int port = config().getInteger("http.port", DEFAULT_HTTP_PORT);
		Router router = Router.router(vertx);

		// Bind "/" to our hello message.
		router.route("/").handler(routingContext -> {
			LOGGER.info("vertx app welcome page...");
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/html").end("<h1>Welcome to Mesos vertx APP</h1>");
		});

		router.route().handler(BodyHandler.create());
		router.get("/vertx/ping").handler(this::getPing);
		router.get("/vertx/error").handler(this::getError);
		router.get("/vertx/exception").handler(this::getException);
		router.route().handler(StaticHandler.create());
		vertx.createHttpServer().requestHandler(router::accept).listen(port);
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}

	/**
	 * Vertx ping
	 * 
	 * @param routingContext
	 */
	private void getPing(RoutingContext routingContext) {
		routingContext.response().setStatusCode(201).putHeader("content-type", "text/html").end("Pong!");
	}

	/**
	 * Vertx Error
	 *
	 * @param routingContext
	 */
	private void getError(RoutingContext routingContext) {
		routingContext.response().setStatusCode(400).putHeader("content-type", "text/html").end("40X Error!");
	}

	/**
	 * Vertx Exception
	 *
	 * @param routingContext
	 */
	private void getException(RoutingContext routingContext) {
		routingContext.response().setStatusCode(505).putHeader("content-type", "text/html").end("50X Exception!");
	}

}
