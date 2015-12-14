package com.lkulig.ratpack.e2e;

import com.lkulig.ratpack.RatpackDemoApplication;
import ratpack.test.MainClassApplicationUnderTest;
import ratpack.test.ServerBackedApplicationUnderTest;
import ratpack.test.http.TestHttpClient;

public abstract class AbstractFunctionalTest {

    protected ServerBackedApplicationUnderTest server = new MainClassApplicationUnderTest(RatpackDemoApplication.class);
    protected TestHttpClient httpClient = server.getHttpClient();

}
