package org.drools.core.concurrent;

import org.drools.Service;

import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;

public interface ExecutorProvider extends Service {

    Executor getExecutor();

    <T> CompletionService<T> getCompletionService();
}
