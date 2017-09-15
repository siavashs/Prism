<a href="https://marketplace.atlassian.com/plugins/com.catawiki.jira.prism"><img width="232" src="src/main/resources/images/jira-software.png"/></a> <a href="https://marketplace.atlassian.com/plugins/com.catawiki.jira.prism"><img width="280" src="src/main/resources/images/service-desk.png"/></a> <a href="https://marketplace.atlassian.com/plugins/com.catawiki.jira.prism"><img width="150" src="src/main/resources/images/marketplace.png"/></a>

Prism is a `{code}` macro plugin for Atlassian Jira.

## Features
* Client side syntax highlighting using [Prism](http://prismjs.com/)
* Support for many programming languages
* Line numbers
* Line highlighting
* Commandline with output support
* Whitespace normalization

## Build
Install **Atlassian Plugin SDK** and run `atlas-package`.

## Usage
Default language is Ruby:

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

### Enabling line numbers

Syntax: `ln` or `linenumbers`

```
{code:ruby|ln}
...
{code}
```

### Highlighting lines

Syntax: `hl=<line(s)>,<range>,...` or `highlight=<line(s)>,<range>,...`

```
{code:ruby|hl=2,4-6}
...
{code}
```

### Showing commandline with optional output

Syntax: `cmd=<user>@<host>[><output line(s),<ranges>,...]` or `commandline=<user>@<host>[><output line(s),<ranges>,...]`

```
{code:bash|cmd=siavash@catawiki>2,5-30}
...
{code}
```

## Issues
Visual editing in Rich Text Editor is not fully supported. While you don't have to disable Rich Text Editor in Jira, you cannot use the **visual tab** to do code editing.

## Notes
This plugin uses a slightly modified version of [Prism](http://prismjs.com/) to fix JS compile issues in Atlassian Plugin SDK.

This plugin is released without any support, if you want to add a new feature or fix a bug feel free to submit a PR.

Also check [this Jira Server issue](https://jira.atlassian.com/browse/JRASERVER-21067) regarding `{code}` macro limitations and why this plugin was developed.
