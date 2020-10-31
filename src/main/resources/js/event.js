AJS.toInit(function(){
  if (JIRA.Version.isGreaterThanOrEqualTo("8.0.0")) {
    console.debug("Prism detected JIRA >= 8.0.0");
    // Highlight (new) content in issue tabs
    JIRA.ViewIssueTabs.onTabReady(function() {
      console.debug("Prism captured event: JIRA.ViewIssueTabs.onTabReady");
      /* Set a timeout since JIRA navigator fires this event too early,
        resulting in code style to be lost!
      */
      setTimeout(function(){
        console.time('Prism.highlightAll');
        Prism.highlightAll();
        console.timeEnd('Prism.highlightAll');
      }, 1000);
    });

  } else {
    console.debug("Prism detected JIRA < 8.0.0");
    // Highlight new content dynamically for older versions of JIRA
    JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e, context, reason) {
      switch(reason) {
        case "pageLoad":
        case "panelRefreshed":
          console.debug("Prism captured event: JIRA.Events.NEW_CONTENT_ADDED, reason:", reason);
          /* Set a timeout since JIRA navigator fires this event too early,
            resulting in code style to be lost!
          */
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
  }
});
