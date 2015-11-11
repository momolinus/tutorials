/**
 * created at 09.11.2015 (16:34:13)
 */
package de.m_bleil.threads;

import java.util.ArrayList;
import java.util.List;

import org.pmw.tinylog.Logger;

public class CountThreads {

    /**
     * @param args
     */
    public static void main(String[] args) {

	Thread currentThread = Thread.currentThread();

	printAllGroups(currentThread);

    }

    public static void printAllThreads(Thread thread) {
	List<ThreadGroup> threadGroups = new ArrayList<>();
	ThreadGroup rootGroup;


	rootGroup = searchForRootGroup(thread);
	threadGroups.add(rootGroup);

	int threadsMaxCount = rootGroup.activeCount() * 10;
	Thread[] allThreads = new Thread[threadsMaxCount];
	int threadsCount = rootGroup.enumerate(allThreads, true);

	if (threadsMaxCount <= threadsCount) {
	    Logger.warn("some threads omitted, threads count == threads max count: {} == {}", threadsCount,
		    threadsMaxCount);
	}

	for (int i = 0; i < threadsCount; i++) {
	    int threadParentsCount;

	    threadParentsCount = getParentsCount(allThreads[i]);

	    Logger.info("thread '{}' in group '{}' is {} a damon", allThreads[i].getName(), allThreads[i].getThreadGroup().getName());
	}
    }

    private static ThreadGroup searchForRootGroup(Thread thread) {
	ThreadGroup rootGroup;
	rootGroup = thread.getThreadGroup();
	while (rootGroup.getParent() != null) {
	    rootGroup = rootGroup.getParent();
	}
	return rootGroup;
    }

    private static int getParentsCount(Thread thread) {
	int parentsCount = 0;

	ThreadGroup rootGroup;
	rootGroup = thread.getThreadGroup();

	while (rootGroup.getParent() != null) {
	    rootGroup = rootGroup.getParent();
	    parentsCount++;
	}
	return parentsCount;
    }

    public static void printAllThreadsCount(Thread thread) {
	ThreadGroup rootGroup;

	rootGroup = searchForRootGroup(thread);

	int threadsMaxCount = rootGroup.activeCount() * 10;
	Thread[] allThreads = new Thread[threadsMaxCount];
	int threadsCount = rootGroup.enumerate(allThreads, true);

	if (threadsMaxCount <= threadsCount) {
	    Logger.warn("some threads omitted, threads count == threads max count: {} == {}", threadsCount,
		    threadsMaxCount);
	}

	Logger.info("thread count {} for root group '{}'", threadsCount, rootGroup.getName());
    }

    // TODO could be wrong
    public static void printAllGroups(Thread thread) {
	ThreadGroup rootGroup;

	rootGroup = searchForRootGroup(thread);

	int groupsMaxCount = rootGroup.activeGroupCount() * 10;
	ThreadGroup[] allGroups = new ThreadGroup[groupsMaxCount];
	int groupsCount = rootGroup.enumerate(allGroups, true);

	if (groupsMaxCount <= groupsCount) {
	    Logger.warn("some groups omitted, groups count == groups max count: {} == {}", groupsCount, groupsMaxCount);
	}

	for (int i = 0; i < groupsCount; i++) {
	    Logger.info("thread  group '{}'", allGroups[i].getName());
	}
    }

}
