<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글목록 페이지</title>
	<link rel="stylesheet" href="../css/style.css" />
    <link rel="stylesheet" href="../plugin/mCustomScrollbar/jquery.mCustomScrollbar.min.css" />
    
</head>
<body>

	<div class="fixed-navbar">
        <div class="pull-left">
            <h1 class="page-title">Hanwha SW Camp 5th</h1>
        </div>
        <div class="pull-right">
            <h1 class="page-title">${ loginUser.name }님이 로그인 하였습니다</h1>
            <button class="btn btn-warning"><a href="/user/logout.hanwha">로그아웃</a></button>
        </div>
    </div>
    
	<div id="wrapper">
        <div class="main-content">
            <div class="row row-inline-block small-spacing">
                <div class="col-xs-12">
                    <div class="box-content">
                    
                    
                    
			<!--  content -->
			<form id="searchForm" class="searchform js__toggle active pull-right" onsubmit="return false;" autocomplete="off">
            <input type="search" class="input-search" placeholder="Search..." />
            <button type="button" class="mdi mdi-magnify button-search"><i class="fa fa-search" aria-hidden="true"></i></button>
	        </form>
	        
	        <div class="table-responsive clearfix">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>등록일</th>
                        <th>조회 수</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var ="item" items="${lst}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td class="text-left"><a href="view.hanwha?idx=${item.idx}">${item.title}</a></td>
                            <td>${item.writer}</td>
                            <td>${item.insertTime}</td>
                            <td>${item.viewCnt}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="btn_wrap text-right">
                <a href="/board/write.hanwha" class="btn btn-primary waves-effect waves-light">등록</a>
            </div>

            <!-- page  -->
            <nav aria-label="Page navigation" class="text-center">
                <ul class="pagination">
                    <li><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                </ul>
            </nav>
        
        </div>
            <!-- card-content -->
            
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

           

        /*]]>*/
        </script>
</body>
</html>
