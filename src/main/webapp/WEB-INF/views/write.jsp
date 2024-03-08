<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글작성 페이지</title>
	<link rel="stylesheet" href="../css/style.css" />
    <link rel="stylesheet" href="../plugin/mCustomScrollbar/jquery.mCustomScrollbar.min.css" />
    
</head>
<body>

	<div class="fixed-navbar">
        <div class="pull-left">
            <h1 class="page-title">Hanwha SW Camp 5th</h1>
        </div>
    </div>
    
	<div id="wrapper">
        <div class="main-content">
            <div class="row row-inline-block small-spacing">
                <div class="col-xs-12">
                    <div class="box-content">
                    
                    
                    
			<!--  content -->
			<div class="card-content">
            <form id="" class="form-horizontal" method="" autocomplete="off">
                
                <div class="form-group">
                    <label for="isNotice" class="col-sm-2 control-label">공지글 설정</label>
                    <div class="col-sm-10" style="margin-top: 10px;">
                        <input  type="checkbox" 
                                id="isNotice" 
                                name="isNotice"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">제목</label>
                    <div class="col-sm-10">
                        <input type="text" id="title" name="title" class="form-control" maxlength="50" placeholder="제목을 입력해 주세요." />
                    </div>
                </div>

                <div class="form-group">
                    <label for="writer" class="col-sm-2 control-label">이름</label>
                    <div class="col-sm-10">
                        <input type="text" id="writer" name="writer" class="form-control" maxlength="10" placeholder="이름을 입력해 주세요." />
                    </div>
                </div>

                <div class="form-group">
                    <label for="content" class="col-sm-2 control-label">내용</label>
                    <div class="col-sm-10">
                        <textarea id="content" name="content" class="form-control" maxlength="1000" placeholder="내용을 입력해 주세요."></textarea>
                    </div>
                </div>

            </form>

            <div class="btn_wrap text-center">
                <a href="./list.hanwha" class="btn btn-default waves-effect waves-light">뒤로가기</a>
                <button type="button" class="btn btn-primary waves-effect waves-light">저장하기</button>
            </div>
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


