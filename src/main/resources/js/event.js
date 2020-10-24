// Highlight new content dynamically for older versions of JIRA
JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e, context, reason) {
  switch(reason) {
    case "pageLoad":
    case "panelRefreshed":
      console.debug("Prism captured event: JIRA.Events.NEW_CONTENT_ADDED, reason:", reason);
      setTimeout(function(){
        console.time('Prism.highlightAllUnder');
        Prism.highlightAllUnder(e.target);
        console.timeEnd('Prism.highlightAllUnder');
      }, 1000);
      break;
    default:
      console.debug("Prism ignored reason:", reason)
  }
});

// Highlight (new) content in issue tabs
JIRA.ViewIssueTabs.onTabReady(function() {
  /* Set a timeout since JIRA navigator fires this event too early,
     resulting in code style to be lost!
  */
  console.debug("Prism captured event: JIRA.ViewIssueTabs.onTabReady");
  setTimeout(function(){
    console.time('Prism.highlightAll');
    Prism.highlightAll();
    console.timeEnd('Prism.highlightAll');
  }, 1000);
});
