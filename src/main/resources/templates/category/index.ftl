<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <!-- 正文内容content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" action="/sell/seller/category/save" method="post">
                        <div hidden class="form-group">
                            <label>类目id</label>
                            <input name="categoryId" type="text" class="form-control" id="categoryId" value="${(productCategory.categoryId)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>名称</label>
                            <input name="categoryName" type="text" class="form-control" id="categoryName" value="${(productCategory.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类别</label>
                            <input name="categoryType" type="text" class="form-control" id="categoryType" value="${(productCategory.categoryType)!''}"/>
                        </div>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>