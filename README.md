# xxhwap
上海外国语大学图书管理系统
上海外国语大学二手书交易平台
大致功能如下：

1：买书

### 在线搜索卖家发布的图书

2：卖书

### 可以通过扫描条形码的方式，获取图书信息直接发布，或者手动输入，上传图片再发布

3：我买的书

### 可以浏览自己买过的书

4：我卖的书

可以浏览自己卖过的书

5：交易成功

### 买家买过书之后，卖家可以点击交易完成，此书离开删除线上图书信息，如果卖家不点击交易成功，7天后此书自动默认交易成功，图书信息7天后自动删除

6：交易失败

### 买家点击交易失败，提示卖家此书继续在平台上出售，且2天内不能撤销出售

7：等待出售

### 卖家发布图书后，是等待出售的状态，此时也可以撤销出售，离开从平台删除图书信息
8：撤销出售

### 卖家点击撤销出售后，离开从平台删除图书信息

开发技术：springMVC+freemarker3.5+ibatis+mysql+jquery+html

此项目需要借助微信公众平台，因为上传图书，扫一扫条形码获取图书都是借助微信jsapi内置接口开发的。

部分页面截图：见wiki https://github.com/zhengyunfei/xxhwap/wiki

             
