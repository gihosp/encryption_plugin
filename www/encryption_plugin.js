
function encryption_plugin(){
}

encryption_plugin.prototype.encrypt = function (options, successCallback,errorCallback) {
  cordova.exec(successCallback, errorCallback, "encryption_plugin", "encrypt",[options]);
};
encryption_plugin.prototype.encrypt_no_url_encode = function (options, successCallback,errorCallback) {
  cordova.exec(successCallback, errorCallback, "encryption_plugin", "encrypt_no_url_encode",[options]);
};
encryption_plugin.prototype.decrypt = function (options, successCallback,errorCallback) {
  cordova.exec(successCallback, errorCallback, "encryption_plugin", "decrypt",[options]);
};

encryption_plugin.prototype.launchapp = function (options, successCallback,errorCallback) {
  cordova.exec(successCallback, errorCallback, "encryption_plugin", "launchapp",[options]);
};

encryption_plugin.install = function () {
  if (!window.plugins) {
    window.plugins = {};
  }

  window.plugins.encryption_plugin = new encryption_plugin();
  return window.plugins.encryption_plugin;
};

cordova.addConstructor(encryption_plugin.install);
