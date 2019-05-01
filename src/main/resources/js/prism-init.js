// Highlight new content
JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e, context, reason) {
  switch(reason) {
    case "pageLoad":
    case "panelRefreshed":
      Prism.highlightAllUnder(e.target);
      break;
    default:
      // console.log("Prism: ignored content,", reason)
  }
});

// Highlight content in issue tabs
JIRA.ViewIssueTabs.onTabReady(function() {
  Prism.highlightAll();
});
