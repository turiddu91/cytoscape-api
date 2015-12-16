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
 * An object that represents an external job.
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule jobs-api
 */
public interface CyJob {
	/**
	 * Return the ID for this job
	 *
	 * @return the job id
	 */
	public String getJobId();

	/**
	 * Return the class ID of the job handler associated with this
	 * {@link CyJob}.  This is used when an App attempts to associate
	 * it's job handler with this job.
	 *
	 * @return the class id of the {@link CyJobHandler}
	 */
	public String getJobHandler();

	/**
	 * Return the polling interval for checking on the job status
	 * in seconds.
	 *
	 * @return the number of seconds between polls
	 */
	public int pollInterval();

	/**
	 * Get the current status of this job
	 *
	 * @return the job status
	 */
	public CyJobStatus getJobStatus();

	/**
	 * Get the results from a job
	 *
	 * @return the job results
	 */
	public CyJobData getJobResults();

	/**
	 * Cancel this job.
	 *
	 * @return the job status
	 */
	public CyJobStatus cancelJob();
}
