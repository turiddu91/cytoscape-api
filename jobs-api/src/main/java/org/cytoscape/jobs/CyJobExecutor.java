package org.cytoscape.jobs;

/*
 * #%L
 * Cytoscape Jobs API (jobs-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2015 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

/**
 * The main interface for execution of external jobs.
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule jobs-api
 */
import java.net.URI;

public interface CyJobExecutor {
	public CyJob executeJob(URI path, String user, String pass, CyJobData jobData);
	// public CyJobTableMarshaller getTableMarshaller();
	// public CyJobNetworkMarshaller getNetworkMarshaller();
	// public CyJobViewMarshaller getViewMarshaller();
	public CyJobMarshaller getMarshaller();
	public CyJobUnmarshaller getUnmarshaller();
	public CyJobStatusChecker getStatusChecker();
	public CyJobFetcher getFetcher();
}