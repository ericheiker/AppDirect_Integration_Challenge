package com.eheiker.appdirect.client;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.eheiker.appdirect.client.action.ActionResult;
import com.eheiker.appdirect.client.action.GetEventAction;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetEventActionTests {

    @InjectMocks
    GetEventAction action;

    @Mock
    AppDirectClient client;

    @Test
    public void testEntitySetOnResult() throws JAXBException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        String resultEntity = "MY_RESULT";

        when(client.signAndGet(anyString(), eq(String.class))).thenReturn(resultEntity);

        ActionResult<String> result = action.execute(String.class);
        assertThat(result.getEntity(), equalTo(resultEntity));
    }

    @Test
    public void testSuccessTrue() {
        ActionResult<String> result = action.execute(String.class);
        assertThat(result.isSuccess(), equalTo(Boolean.TRUE));
    }

    @Test
    public void testSuccessFalseOnException() throws JAXBException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        when(client.signAndGet(anyString(), eq(String.class))).thenThrow(new IOException());
        ActionResult<String> result = action.execute(String.class);
        assertThat(result.isSuccess(), equalTo(Boolean.FALSE));
    }

    @Test
    public void testErrorMessageOnException() throws JAXBException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        String errorMsg = "OH NOES";
        when(client.signAndGet(anyString(), eq(String.class))).thenThrow(new IOException(errorMsg));
        ActionResult<String> result = action.execute(String.class);
        assertThat(result.getErrorMessage(), equalTo(errorMsg));
    }

}
