package com.hubertyoung.compiler;

import com.google.auto.service.AutoService;
import com.hubertyoung.base.annotation.Environment;
import com.hubertyoung.base.bean.EnvironmentBean;
import com.squareup.javapoet.FieldSpec;

import javax.annotation.processing.Processor;
import javax.lang.model.element.Modifier;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/19 10:56
 * @since:V$VERSION
 * @desc:com.hubertyoung.compiler
 */
@AutoService( Processor.class )
public class CommonSwitcherCompilerRelease extends CommonSwitcherCompilerDebug {

	@Override
	protected FieldSpec generateEnvironmentField(Environment environmentAnnotation,
												 FieldSpec.Builder defaultXXEnvironmentFiledBuilder,
												 String moduleUpperCaseName,
												 String environmentName,
												 String environmentUpperCaseName,
												 String url,
												 String alias) {
		if (environmentAnnotation.isRelease()) {
			return super.generateEnvironmentField(environmentAnnotation, defaultXXEnvironmentFiledBuilder, moduleUpperCaseName, environmentName, environmentUpperCaseName, url, alias);
		}

		return FieldSpec.builder(EnvironmentBean.class,
				String.format("%s_%s%s", moduleUpperCaseName, environmentUpperCaseName, VAR_DEFAULT_ENVIRONMENT_SUFFIX),
				Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
				.initializer(String.format("new %s(\"%s\", \"%s\", \"%s\", %s%s)",
						EnvironmentBean.class.getSimpleName(), environmentName, "", alias, VAR_MODULE_PREFIX, moduleUpperCaseName))
				.build();
	}
}
