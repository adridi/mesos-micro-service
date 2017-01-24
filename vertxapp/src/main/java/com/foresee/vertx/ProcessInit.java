package com.foresee.vertx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Consumer;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;

/**
 * 
 * @author Abdel Dridi
 *
 *         Foresee Inc Copyright 2017
 *
 */
public class ProcessInit {

	private static final String CONFIG_FILE = "src/main/config/conf.json";

	public static void run(Class clazz) {

		run(clazz, new VertxOptions().setClustered(false), getDeploymemtOptions());
	}

	public static void run(Class clazz, VertxOptions options, DeploymentOptions deploymentOptions) {
		String verticleID = clazz.getName();

		System.setProperty("vertx.cwd", "/");
		Consumer<Vertx> runner = vertx -> {
			try {
				if (deploymentOptions != null) {
					vertx.deployVerticle(verticleID, deploymentOptions);
				} else {
					vertx.deployVerticle(verticleID);
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		};
		if (options.isClustered()) {
			Vertx.clusteredVertx(options, res -> {
				if (res.succeeded()) {
					Vertx vertx = res.result();
					runner.accept(vertx);
				} else {
					res.cause().printStackTrace();
				}
			});
		} else {
			Vertx vertx = Vertx.vertx(options);
			runner.accept(vertx);
		}
	}

	private static DeploymentOptions getDeploymemtOptions() {
		JsonObject conf = null;
		try (Scanner scanner = new Scanner(new File(CONFIG_FILE)).useDelimiter("\\A")) {
			String sconf = scanner.next();
			try {
				conf = new JsonObject(sconf);
			} catch (DecodeException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			try {
				conf = new JsonObject(CONFIG_FILE);
			} catch (DecodeException e2) {
				e2.printStackTrace();
			}
		}

		if (conf != null) {
			DeploymentOptions deploymentOptions = new DeploymentOptions();
			deploymentOptions.setConfig(conf);
			return deploymentOptions;
		}
		return null;
	}
}
