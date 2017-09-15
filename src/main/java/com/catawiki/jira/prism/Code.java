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

        // Default language
        String language = "ruby";

        // Code body
        templateParams.put("content", body);

        // line numbers (optional)
        if (parameters.containsValue("linenumbers") || parameters.containsValue("ln")) {
            templateParams.put("linenumbers", true);
        }

        // wrap (optional)
        if (parameters.containsValue("wrap")) {
            templateParams.put("wrap", true);
        }

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            switch (entry.getKey()) {
                case "language":
                case "lang":
                    String parameter = (String) entry.getValue();
                    if (checkLanguage(parameter)) {
                        language = parameter;
                    }
                    break;
                case "title":
                    templateParams.put("title", (String) entry.getValue());
                    break;
                case "highlight":
                case "hl":
                    templateParams.put("highlight", (String) entry.getValue());
                    break;
                case "commandline":
                case "cmd":
                    String cmd = (String) entry.getValue();
                    String parts[] = cmd.split(">");
                    String prompt[] = parts[0].split("@");
                    String output = parts[1];
                    String user = prompt[0];
                    String host = prompt[1];
                    templateParams.put("commandline", true);
                    templateParams.put("user", user);
                    templateParams.put("host", host);
                    templateParams.put("output", output);
                    break;
                default:
            }
        }

        // Check for language as first parameter
        if (parameters.containsKey("0")) {
            String parameter = (String) parameters.get("0");
            if (checkLanguage(parameter)) {
                language = parameter;
            }
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

    private Boolean checkLanguage(String language) {
        switch (language) {
            case "abap":
            case "actionscript":
            case "ada":
            case "apacheconf":
            case "apl":
            case "applescript":
            case "asciidoc":
            case "aspnet":
            case "autohotkey":
            case "autoit":
            case "bash":
            case "basic":
            case "batch":
            case "bison":
            case "brainfuck":
            case "bro":
            case "c":
            case "clike":
            case "coffeescript":
            case "cpp":
            case "crystal":
            case "csharp":
            case "css":
            case "css-extras":
            case "d":
            case "dart":
            case "diff":
            case "django":
            case "docker":
            case "eiffel":
            case "elixir":
            case "erlang":
            case "fortran":
            case "fsharp":
            case "gherkin":
            case "git":
            case "glsl":
            case "go":
            case "graphql":
            case "groovy":
            case "haml":
            case "handlebars":
            case "haskell":
            case "haxe":
            case "http":
            case "icon":
            case "inform7":
            case "ini":
            case "j":
            case "jade":
            case "java":
            case "javascript":
            case "jolie":
            case "json":
            case "jsx":
            case "julia":
            case "keyman":
            case "kotlin":
            case "latex":
            case "less":
            case "livescript":
            case "lolcode":
            case "lua":
            case "makefile":
            case "markdown":
            case "markup":
            case "matlab":
            case "mel":
            case "mizar":
            case "monkey":
            case "nasm":
            case "nginx":
            case "nim":
            case "nix":
            case "nsis":
            case "objectivec":
            case "ocaml":
            case "oz":
            case "parigp":
            case "parser":
            case "pascal":
            case "perl":
            case "php":
            case "php-extras":
            case "powershell":
            case "processing":
            case "prolog":
            case "properties":
            case "protobuf":
            case "puppet":
            case "pure":
            case "python":
            case "q":
            case "qore":
            case "r":
            case "reason":
            case "rest":
            case "rip":
            case "roboconf":
            case "ruby":
            case "rust":
            case "sas":
            case "sass":
            case "scala":
            case "scheme":
            case "scss":
            case "smalltalk":
            case "smarty":
            case "sql":
            case "stylus":
            case "swift":
            case "tcl":
            case "textile":
            case "twig":
            case "typescript":
            case "vbnet":
            case "verilog":
            case "vhdl":
            case "vim":
            case "wiki":
            case "xojo":
            case "yaml":
                return true;
            default:
                return false;
        }
    }
}
