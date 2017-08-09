Prism is a `{code}` macro plugin for Atlassian JIRA.

## Features
* Support for many programming languages
* Client side syntax highlighting using [Prism](http://prismjs.com/)

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

You can specify another language:

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

## Issues
Visual editing in Rich Text Editor is not supported. While you don't have to disable Rich Text Editor in JIRA, you cannot use the **visual tab** to do code editing.

## Notes
This plugin uses a slightly modified version of [Prism](http://prismjs.com/) to fix JS compile issues in Atlassian Plugin SDK.

This plugin is released without any support, if you want to add a new feature or fix a bug feel free to submit a PR.

Also check [this JIRA Server issue](https://jira.atlassian.com/browse/JRASERVER-21067) regarding `{code}` macro limitations and why this plugin was developed.
