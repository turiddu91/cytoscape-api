package org.cytoscape.webservice.client;

import java.net.URI;

import org.cytoscape.io.webservice.client.AbstractWebServiceClient;
import org.cytoscape.webservice.AbstractWebServiceClientTest;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.junit.Before;

import static org.mockito.Mockito.*;

public class AbstractClientTest extends AbstractWebServiceClientTest {

	@Before
	public void setUp() throws Exception {
		final String uriString = "http://www.ebi.ac.uk/Tools/webservices/psicquic/registry/registry";
		this.locationUri = new URI(uriString);
		this.description = "dummy client";
		this.displayName = "dummy";
		this.queryObject = "test query";
		client = new DummyClient(uriString, displayName, description);
	}
	
	private final class DummyClient extends AbstractWebServiceClient {

		private Task mockTask = mock(Task.class);
		
		public DummyClient(String uri, String displayName, String description) {
			super(uri, displayName, description);
		}

		@Override
		public TaskIterator createTaskIterator(Object query) {
			return new TaskIterator(mockTask);
		}
		
	}
}
