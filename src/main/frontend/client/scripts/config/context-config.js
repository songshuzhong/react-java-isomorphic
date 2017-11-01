const context = {
  contextPath: 'http://coptest.bonc.yz/mdocs', //内容服务接口
  cop_internet: 'http://coptest.bonc.yz', //互联网服务的后台接口

  //配置路径时，只需配置以上两个变量
  //注意：配置cop_internet时不需要加mdocs

  zb_path : 'http://coptest.bonc.pro/mdocs',
  three_path : 'http://copprep.cop.local:8081/mdocs',
  yz_path : 'http://coptest.bonc.yz/mdocs',

  namespace: 'cop-pangu'
};

module.exports = context;