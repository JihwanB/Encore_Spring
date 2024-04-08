<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글작성 페이지</title>
    <link rel="stylesheet" href="../css/style.css"/>
    <link rel="stylesheet" href="../plugin/mCustomScrollbar/jquery.mCustomScrollbar.min.css"/>

</head>

<body>

<div class="fixed-navbar">
    <div class="pull-left">
        <h1 class="page-title">Hanwha SW Camp 5th</h1>
    </div>
    <div class="pull-right">
        <h1 class="page-title">${ loginUser.name }님 로그인</h1>
        <button class="btn btn-warning"><a href="/user/logout.hanwha">로그아웃</a></button>

    </div>
</div>

<div id="wrapper">
    <div class="main-content">
        <div class="row row-inline-block small-spacing">
            <div class="col-xs-12">
                <div class="box-content">

                    <input type="hidden" id="idx" value="${response.idx}"/>
                    <input type="hidden" id="writer" value="${loginUser.id}"/>


                    <div class="card-content">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">제목</label>
                            <div class="col-sm-10">
                                <p class="form-control" id="title">${response.title}</p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">이름</label>
                            <div class="col-sm-10">
                                <p class="form-control" id="writer">${response.writer}</p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">내용</label>
                            <div class="col-sm-10">
                                <p class="form-control" id="content">${response.content}</p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">등록일</label>
                            <div class="col-sm-10">
                                <p class="form-control">${response.insertTime}</p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">조회 수</label>
                            <div class="col-sm-10">
                                <p class="form-control" id="viewCnt">${response.viewCnt}</p>
                            </div>
                        </div>

                        <div class="btn_wrap text-center">
                            <a href="javascript:listPage()"
                               class="btn btn-default waves-effect waves-light"
                               onclick="listPage();">뒤로가기</a>

                            <c:if test="${ loginUser.id == response.writer }">
                                <a href="javascript:void(0)"
                                   class="btn btn-primary waves-effect waves-light"
                                   onclick="writePage();">수정하기</a>

                                <%--
                                <a  href="/board/delete.hanwha?idx=${response.idx}"
                                    class="btn btn-danger waves-effect waves-light"
                                                        >삭제하기</a>
                                --%>

                                <button type="button"
                                        class="btn btn-danger waves-effect waves-light"
                                        id="delBtn">삭제하기
                                </button>
                            </c:if>
                        </div>
                    </div>

                    <div class="box-content">
                        <div class="card-content">
                            <div class="clearfix">
                                <h4 class="box-title pull-left">Comment</h4>
                            </div>

                            <form class="form-horizontal form-view">
                                <div class="input-group margin-bottom-20">
                                    <input id="commentContent" type="text" class="form-control" placeholder="">
                                    <div class="input-group-btn">
                                        <button id="commentSaveBtn" type="button" class="btn waves-effect waves-light">
                                            <i class="fa fa-commenting" aria-hidden="true"></i>
                                        </button>
                                    </div>
                                </div>

                                <ul class="notice-list" id="comment-list">
                                    <c:forEach items="${commentlist}" var="comment">
                                        <li>
                                            <span class="name">${comment.writer}</span>
                                            <span class="desc">${comment.content}</span>
                                            <span class="time">${comment.regdate}</span>
                                            <button onclick="commentDel(${comment.id}, ${response.idx})" type="button"
                                                    class="btn btn-xs btn-circle">
                                                <i class="fa fa-close" aria-hidden="true"></i>
                                            </button>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <footer class="footer">
                <ul class="list-inline">
                    <li>2024 ⓒ Encore</li>
                </ul>
            </footer>
        </div>
    </div>
</div>
<script src="../scripts/common.js"></script>
<script src="../scripts/jquery.min.js"></script>
<script src="../scripts/main.js"></script>
<script src="../plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="../plugin/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js"></script>


<script>
    /*<![CDATA[*/
    $(document).ready(function () {
        $("#delBtn").click(function () {
            //window.alert("버튼을 클릭하였습니다.....");
            //console.log(>>>>>>>>>>); //sout과 유사한 개념 in front!
            //console.log($("idx").val() );
            //console.log(>>>>>>>>>>);
            const idx = $("#idx").val()
            if (!confirm(idx + "번 게시글을 삭제하시겠습니까? ")) {
                return false;
            } else {
                location.href = "/board/delete.hanwha?idx=" + idx;
            }
        });
        $("#commentSaveBtn").click(function () {
            //window.alert("comment save click");
            //게시글 idx, writer, comment msg
            //json{} -> server
            let idx = $("#idx").val();
            let writer = $("#writer").val();
            let commentContent = $("#commentContent").val();
            let jsonData = {
                idx: idx,
                writer: writer,
                content: commentContent
            };
            //window.alert(jsonData);
            var html = "";
            $.ajax({
                url: "../board/commentSave.hanwha",
                type: "get",
                data: jsonData,
                dataType: "json",
                success: function (lst) {
                    $("#comment-list").empty();
                    $.each(lst, function (index, comment) {
                        html += '<li>';
                        html += '<span class="name">' + comment.writer + '</span>';
                        html += '<span class="desc">' + comment.content + '</span>';
                        html += '<span class="time">' + comment.regdate + '</span>';
                        html += '<button onclick="commentDel(' + comment.id + ' , ' + $("#idx").val() + ')" type="button" class="btn btn-xs btn-circle"><i class="fa fa-close" aria-hidden="true"></i></button>';
                        html += '</li>';
                    });
                    window.alert(html);
                    $("#comment-list").append(html);

                }
            });
            $("#commentContent").val("");

        });

    }); // jQuery document.ready end

    function commentDel(id, idx) {
        window.alert("comment id = " + id + " , " + idx);
        var html = "";
        $.ajax({
            url: "../board/commentDel.hanwha",
            type: "post",
            data: {id: id, idx: idx},
            dataType: "json",
            success: function (lst) {
                $("#comment-list").empty();
                $.each(lst, function (index, comment) {
                    html += '<li>';
                    html += '<span class="name">' + comment.writer + '</span>';
                    html += '<span class="desc">' + comment.content + '</span>';
                    html += '<span class="time">' + comment.regdate + '</span>';
                    html += '<button onclick="commentDel(' + comment.id + ')" type="button" class="btn btn-xs btn-circle"><i class="fa fa-close" aria-hidden="true"></i></button>';
                    html += '</li>';
                });
                window.alert(html);
                $("#comment-list").append(html);

            }
        });

    }

    function displayComment(lst) {
        $("#comment-list").empty();
        let html = "";
        window.alert(lst);

        $.each(lst, function (index, comment) {
            html += `<li>`;
            html += `<span class="name">${comment.writer}</span>`;
            html += `<span class="desc">${comment.content}</span>`;
            html += `<span class="time">${comment.regdate}</span>`;
            html += `<button type="button" class="btn btn-xs btn-circle"><i class="fa fa-close" aria-hidden="true"></i></button>`;
            html += `</li>`;
        });
        window.alert(html);
        $("#comment-list").append(html);
    }

    function listPage() {
        console.log("debug >>> listPage() call");
        const queryString = new URLSearchParams(location.search);
        console.log("debug >>> listPage() : " + queryString);
        location.href = "/board/list.hanwha?";
    }

    /*]]>*/
</script>
</body>
</html>
