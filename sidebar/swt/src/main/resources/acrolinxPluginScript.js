(function(){

window.java = {
  log: function(args){
    overwriteJSLoggingP(args);
  },
  error: function(args){
    overwriteJSLoggingP(args);
  }
}

if(window.console && console.log){
    var old = console.log;
    var oldError = console.error;
    console.log = function(){
      old.apply(this, arguments);
      var args = [].slice.apply(arguments);
      var msg = '';
      args.forEach(function(arg){
        msg += JSON.stringify(arg)
      })
      if(window.java) {
        java.log(msg);
      }
    }
    console.error = function(){
      oldError.apply(this, arguments);
      var args = [].slice.apply(arguments);
      var msg = '';
      args.forEach(function(arg){
        msg += JSON.stringify(arg)
      })
      if(window.java) {
        java.error(msg);
      }
  }
}

console.log("TEST");


window.acrolinxPlugin =
{
  requestInit: function(){
    acrolinxSidebar.init(JSON.parse(getInitParamsP()));
  },
  onInitFinished: function(finishResult){
   onInitFinishedNotificationP(JSON.stringify(finishResult))
  },
  requestGlobalCheck: function(){
    acrolinxSidebar.checkGlobal(getTextP(),
      {inputFormat: getInputFormatP(), requestDescription: {documentReference: getDocUrlP()}}
    );
  },
  onCheckResult: function(checkResult){
    onCheckResultP(JSON.stringify(checkResult))
  },
  selectRanges: function(checkId, matches){
    selectRangesP(checkId, JSON.stringify(matches));
  },
  replaceRanges: function(checkId, matches){
    replaceRangesP(checkId, JSON.stringify(matches));
  },
  configure: function(acrolinxPluginConfiguration){
    notifyAboutSidebarConfigurationP(JSON.stringify(acrolinxPluginConfiguration));
  },
  download: function(downloadInfo){
    downloadP(JSON.stringify(downloadInfo));
  },
  openWindow: function(openWindowParams){
    openWindowP(JSON.stringify(openWindowParams));
  }
}
})();