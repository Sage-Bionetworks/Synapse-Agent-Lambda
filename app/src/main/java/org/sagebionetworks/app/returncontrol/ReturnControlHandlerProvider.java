package org.sagebionetworks.app.returncontrol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.sagebionetworks.app.returncontrol.handler.ReturnControlHandler;

public class ReturnControlHandlerProvider {

	private final Map<String, ReturnControlHandler> handlerMap;

	public ReturnControlHandlerProvider(List<ReturnControlHandler> handlers) {
		handlerMap = new HashMap<>();
		handlers.stream().forEach((h) -> {
			handlerMap.put(createKey(h.getActionGroup(), h.getFunction()), h);
		});
	}

	static String createKey(String actionGroup, String function) {
		Objects.requireNonNull(actionGroup, "actionGroup");
		Objects.requireNonNull(function, "function");
		return String.format("%s-%s", actionGroup, function);
	}

	/**
	 * Attempt to find a handler for the provide actionGroup and function.
	 *
	 * @param actionGroup
	 * @param function
	 * @return {@link Optional#empty()} when no handler can be found.
	 */
	public Optional<ReturnControlHandler> getHandler(String actionGroup, String function) {
		return Optional.ofNullable(handlerMap.get(createKey(actionGroup, function)));
	}
}
