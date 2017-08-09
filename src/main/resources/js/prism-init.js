AJS.toInit(function(){
  Prism.highlightAll();
});

JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e, context, reason) {
  Prism.highlightAll();
});
