# Prism has reached it's end of life :older_adult:
This plugin has reached it's EOL and is no longer developed or supported.
There are different reasons behind this decision:
- [Atlassian have ended sales for new Server licenses, and will end support for server on February 15, 2024](https://confluence.atlassian.com/support/atlassian-support-end-of-life-policy-201851003.html): This means that there is no future for JIRA software and this plugin is not portable to JIRA Cloud for technical reasons (you can complain to Atlassian about it).
- Hosting an application on Atlassian marketplace is not fun: apart from technical issues, there is demanding work to prove you application is still compatible with their policies for Data Center application while Atlassian threatens you that you free application will be removed. This is a tedious for an Open Source project which is offered for free (or I'm lazy!)
- The community did not contribute or support the project: Multiple companies reached out in the past year after no new versions of the plugin were released. While they demanded a new release they never offered to contribute in development, testing or financially supporting the project by donations or hiring a developer to help the project.
- I don't have free time to work on the plugin.
- I do not use JIRA anymore.
- I've lost interest.

Feel free to fork and improve this project.

Cheers,\
Siavash

---

<p>
  <a href="https://marketplace.atlassian.com/plugins/com.catawiki.jira.prism">
    <div align="center">
      <div>
        <img src="src/main/resources/images/logo.svg"/>
      </div>
      <div>
        <img height="25" src="src/main/resources/images/marketplace.svg"/>
      </div>
      <div>
        <img height="25" src="src/main/resources/images/jira-software.svg"/>
      </div>
      <div>
        <img height="25" src="src/main/resources/images/jira-service-desk.svg"/>
      </div>
    </div>
  </a>
</p>

Prism is a `{code}` macro plugin for Atlassian Jira.

![Code scan](https://github.com/siavashs/Prism/workflows/Code%20scan/badge.svg)

## Features
* Client side syntax highlighting using [Prism](http://prismjs.com/)
* Support for many [programming languages](http://prismjs.com/#languages-list)
* Line numbers
* Line highlighting
* Commandline with output support
* Whitespace normalization

## Supported Versions

| Name         | Version
|--------------|-----------------
| JIRA         | 7.2.0+
| Service Desk | 3.2.0+

## Build
Install [**Atlassian Plugin SDK**](https://developer.atlassian.com/docs/getting-started/set-up-the-atlassian-plugin-sdk-and-build-a-project/install-the-atlassian-sdk-on-a-linux-or-mac-system) and run `atlas-package`.

## Development
To run a test instance of JIRA, JIRA Software and Service Desk run the following command:
```shell
atlas-run
```
All versions are specified in [`pom.xml`](./pom.ml).

To run specific version of the JIRA server and software either edit [`pom.xml`](./pom.ml) or pass the versions as commandline options:
```shell
atlas-run -v 7.11.0 -D jira.version=7.11.0 -D servicedesk.version=3.14.0
```
**Important:** Make sure to use a compatible `servicedesk.version`, check [compatible versions](https://marketplace.atlassian.com/apps/1213632/jira-service-desk/version-history).

## Usage

Multiple parameters can be specified by using `|` as separator, some parameters can have one or more values separated by `,`:

```
{code:<param1>|<param2>=<value>|<param3>=<value1>,<value2>|...}
...
{code}
```

Default language is **Ruby**:

```
{code}
class Bar < Foo
  def initialize(a)
    @a = a
  end
end
{code}
```

### Selecting language
Syntax: `<language>` as first parameter or `language=<language>` or `lang=<language>`

```
{code:java}
public class Bar extends Foo {
  private int a;

  public Bar(int a) {
    this.a = a;
  }
}
{code}
```

### Setting title
Syntax: `title=<title>`

```
{code:title=prism.rb}
...
{code}
```

### Enabling line numbers
Syntax: `linenumbers` or `ln`

```
{code:ln}
...
{code}
```

#### Setting start number
Syntax: `start=<number>` or `firstline=<number>` or `fl=<number>`

```
{code:ln|start=5}
...
{code}
```

#### Enabling soft line wraps
Syntax: `wrap`

```
{code:ln|wrap}
...
{code}
```

### Highlighting lines
Syntax: `highlight=<line(s)>,<range>,...` or `hl=<line(s)>,<range>,...`

```
{code:hl=2,4-6}
...
{code}
```

### Showing commandline with optional output
Syntax: `cmd=<user>@<host>[><output line(s),<ranges>,...]` or `commandline=<user>@<host>[><output line(s),<ranges>,...]`

```
{code:bash|cmd=siavash@localhost>2,5-30}
...
{code}
```

## Known Issues
Visual editing in Rich Text Editor is not fully supported. While you don't have to disable Rich Text Editor in Jira, you cannot use the **visual tab** to do code editing.

## Notes
This plugin uses a slightly modified version of [Prism](http://prismjs.com/) to fix minor bugs with line highlighting.

This plugin is released without any support, if you want to add a new feature or fix a bug feel free to [submit a PR](https://github.com/siavashs/Prism/pull/new/master).

Also check [this Jira Server issue](https://jira.atlassian.com/browse/JRASERVER-21067) regarding `{code}` macro limitations and why this plugin was developed.

## Privacy
see [PRIVACY.md](PRIVACY.md)
