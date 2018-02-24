package com.urbanfit.apiserver.system.idempotency.strategy;

import com.urbanfit.apiserver.system.idempotency.strategy.AbstractCacheStrategy;

/**
 * 仅缓存策略
 * cache + server control
 * 
 * @author sunshibo
 */
public class OnlyCacheStrategy extends AbstractCacheStrategy {

	@Override
	public String getName() {
		return "OnlyCache";
	}
}