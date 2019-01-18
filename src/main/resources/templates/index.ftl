<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>导出PDF</title>

    <!-- Bootstrap -->
    <link href="${base}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/js/webuploader/webuploader.css" rel="stylesheet">

    <style>
        .main{
            min-height:100vh;
            width: 100vw;
        }
        .title{
            padding: 20px 0;
        }
        .progress-wrapper{
            padding: 20px 0;
        }
        .content{
            /*width: 80%;*/
            overflow: hidden;
        }
    </style>

</head>
<body>

<div class="main container-fluid">
    <div class="title text-center">
        <h2>导出PDF <small>导出</small> </h2>
    </div>
    <div class="operate">
        <#--<button type="button" class="btn btn-primary">导入EXCEL表</button>-->
        <div id="picker">导入Excel表</div>
        <#--<button type="button" class="btn btn-primary" id="picker" >导入Excel表</button>-->
        <button type="button" class="btn btn-primary" id="generatePdf" >生成PDF</button>
        <div class="progress-wrapper" style="display:none">
            <div class="progress" >
                <div class="progress-bar progress-bar-success progress-bar-striped"  role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
                    <span class="sr-only">40% Complete (success)</span>
                </div>
            </div>
        </div>
    </div>
    <div class="content">
        <table class="table table-hover table-bordered">
            <caption>悬停表格布局</caption>
            <thead>
            <tr>
                <th>名称</th>
                <th>城市</th>
                <th>邮编</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Tanmay</td>
                <td>Bangalore</td>
                <td>560001</td>
            </tr>
            <tr>
                <td>Sachin</td>
                <td>Mumbai</td>
                <td>400003</td>
            </tr>
            <tr>
                <td>Uma</td>
                <td>Pune</td>
                <td>411027</td>
            </tr>
            <tr>
                <td>Uma</td>
                <td>Pune</td>
                <td>411027</td>
            </tr>
            <tr>
                <td>Uma</td>
                <td>Pune</td>
                <td>411027</td>
            </tr>
            <tr>
                <td>Uma</td>
                <td>Pune</td>
                <td>411027</td>
            </tr>
            <tr>
                <td>Uma</td>
                <td>Pune</td>
                <td>411027</td>
            </tr>
            <tr>
                <td>Uma</td>
                <td>Pune</td>
                <td>411027</td>
            </tr>
            <tr>
                <td>Uma</td>
                <td>Pune</td>
                <td>411027</td>
            </tr>
            <tr>
                <td>Uma</td>
                <td>Pune</td>
                <td>411027</td>
            </tr>
            <tr>
                <td>Uma</td>
                <td>Pune</td>
                <td>411027</td>
            </tr>
            <tr>
                <td>Uma</td>
                <td>Pune</td>
                <td>411027</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>




<#--<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-2 control-label">
            <h3>导出PDF</h3>
        </div>
        <div class="col-sm-8 control-label"></div>
    </div>

    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-2 btns">
            <div id="picker" class="col-sm-6">导入Excel表</div>
        </div>

        <div class="col-sm-8"></div>
    </div>

    <div class="row">
        <div class="col-sm-2 control-label"></div>
        <div class="col-sm-2">
            <input type="button" class="btn btn-primary uploadBtn" id="generatePdf" value="生成&nbsp;&nbsp;PDF" />
        </div>
        <div class="progress" id="progress" style="display: none">
            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                60%
            </div>
        </div>
        <div class="col-sm-2 control-label"></div>
    </div>
</div>-->


<script src="${base}/js/jquery-3.3.1.min.js"></script>
<script src="${base}/js/bootstrap.min.js"></script>
<script src="${base}/js/webuploader.min.js"></script>
<script src="${base}/js/layer.js"></script>
<script type="text/javascript" src="${base}/js/upload.js"></script>

<script>

    var partnetId=0;
    $(document).ready(function(){

        $("#generatePdf").click(function () {
            $.ajax({
                url:"${base}/generate/pdf",
                type:"post",
                dataType:"json",
                timeout : 100000,
                beforeSend:function () {
                    showProgressbar()
                },
                complete:function () {
                    hideProgressbar()
                },
                success:function(result){
                    layer.alert('导出成功！', {
                        icon: 1,
                        skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
                    })
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    layer.alert('导出失败！', {
                        icon: 2,
                        skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
                    })
                }
            });
        });



    });

    function showProgressbar() {
        $(".progress-wrapper").css("display", "block")

        window.setTimeout(function () {
            var timer = window.setInterval(function () {
                $.ajax({
                    type: 'get',
                    dataType: 'json',
                    url: "${base}/pdf/progress",
                    success: function (data) {
                        $("#progress").text(data.percentText);
                        $("#progress").css("width",data.percentText);
                        if (data.curCount ==  data.totalCount) {
                            // hideProgressbar()
                        }

                        /*$("#Progress .circle-text").text(data.percentText);
                        if (data.curCount === undefined || data.totalCount === undefined) {
                            $("#Progress .circle-info").text("导出进度");
                        } else {
                            $("#Progress .circle-info").text("导出进度:" + data.curCount + "/" + data.totalCount);
                        }
                        if (data.percent == "100") {
                            window.clearInterval(timer);
                            hideProgress();
                        }*/
                    },
                    error: function (data) {
                    }
                });
            }, 500);
        }, 500);
    }

    function hideProgressbar() {
        console.log("隐藏进度条")
        // $("#progress").css("display","inline")
    }

    jQuery(function() {
        var $ = jQuery,
                $list = $('#thelist'),
                $btn = $('#ctlBtn'),
                state = 'pending',
                uploader;

        uploader = WebUploader.create({
            auto: true,// 选完文件后，是否自动上传。
            resize: false,
            swf:'js/webuploader/Uploader.swf',
            server: "${base}/file/upload",// 文件接收服务端。
            pick: '#picker',// 内部根据当前运行是创建，可能是input元素，也可能是flash.
            accept: {
                title: 'Excel',
                extensions: 'xlsx',
                mimeTypes: 'xlsx/!*'
            }
        });

        // 当有文件添加进来的时候
        uploader.on( 'fileQueued', function( file ) {
        });

       // 文件上传过程中创建进度条实时显示。
        uploader.on( 'uploadProgress', function( file, percentage ) {
            layer.msg('上传中...');
        });

        uploader.on( 'uploadSuccess', function( file ) {
            /*$( '#'+file.id ).find('p.state').text('已上传');*/
            layer.alert('上传成功！', {
                icon: 1,
                skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
            })
        });

        uploader.on( 'uploadError', function( file ) {
            layer.alert('上传失败！', {
                icon: 2,
                skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
            })
            /*$( '#'+file.id ).find('p.state').text('上传出错');*/
        });

        uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').fadeOut();
        });

        uploader.on( 'all', function( type ) {
            if ( type === 'startUpload' ) {
                state = 'uploading';
            } else if ( type === 'stopUpload' ) {
                state = 'paused';
            } else if ( type === 'uploadFinished' ) {
                state = 'done';
            }

            if ( state === 'uploading' ) {
                $btn.text('暂停上传');
            } else {
                $btn.text('开始上传');
            }
        });

        $btn.on( 'click', function() {
            if ( state === 'uploading' ) {
                uploader.stop();
            } else {
                uploader.upload();
            }
        });
    });
</script>
</body>
</html>