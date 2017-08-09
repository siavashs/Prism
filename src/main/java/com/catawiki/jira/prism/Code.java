package com.catawiki.jira.prism;

import com.atlassian.jira.template.soy.SoyTemplateRendererProvider;
import com.atlassian.renderer.RenderContext;
import com.atlassian.renderer.v2.RenderMode;
import com.atlassian.renderer.v2.macro.BaseMacro;
import com.atlassian.renderer.v2.macro.MacroException;
import com.atlassian.soy.renderer.SoyException;
import com.atlassian.soy.renderer.SoyTemplateRenderer;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class Code extends BaseMacro {

    private static final String FALLBACK_RENDER_OUTPUT = "{noformat}%s{noformat}";

    private final SoyTemplateRenderer soyTemplateRenderer;

    public Code(final SoyTemplateRendererProvider soyTemplateRendererProvider) {
        super();
        this.soyTemplateRenderer = soyTemplateRendererProvider.getRenderer();
    }

    @Override
    public boolean hasBody() {
        return true;
    }

    @Override
    public RenderMode getBodyRenderMode() {
        return RenderMode.allow(RenderMode.F_NONE);
    }

    @Override
    public String execute(Map<String, Object> parameters, String body, RenderContext renderContext) throws MacroException {

        ImmutableMap.Builder<String, Object> templateParams = ImmutableMap.builder();
        templateParams.put("content", body);
        String language = "ruby";
        if (parameters.get("0") != null) {
          language = (String) parameters.get("0");
        }
        templateParams.put("language", language);

        try {
            return this.soyTemplateRenderer.render(
                    "com.catawiki.jira.prism:handler",
                    "Prism.Macros.Code.html",
                    templateParams.build());
        } catch (SoyException e) {
            return String.format(FALLBACK_RENDER_OUTPUT, body);
        }
    }
}
