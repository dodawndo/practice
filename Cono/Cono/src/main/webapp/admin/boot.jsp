<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>
<link rel='stylesheet' href='//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css'>
<style>
.bd-navbar {
  position: sticky;
  top: 0;
  z-index: 1071;
  min-height: 4rem;
  box-shadow: 0 0.5rem 1rem rgba(0,0,0,.05), inset 0 -1px 0 rgba(0,0,0,.1);
}
.bd-sidebar {
  position: sticky;
  top: 4rem;
  z-index: 1000;
  height: calc(100vh - 4rem);
  background: #eee;
  border-right: 1px solid rgba(0,0,0,.1);
  max-width: 220px;
  display: flex;
  padding: 0;
  overflow-y: hidden;
  flex-direction: column;
}
.bd-sidebar-body {
  height: 100%;
  overflow-y: auto;
  display: block;
}
.bd-sidebar-body .nav {
  display: block;
}
.bd-sidebar-body .nav>li>a {
  display: block;
  padding: .25rem 1.5rem;
  font-size: 90%;
}
.bd-sidebar-footer {
  padding: 1rem;
  background: #ddd;
}
</style>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>

<header class="navbar navbar-expand navbar-dark bg-dark bd-navbar">
  <a class="navbar-brand" href="#">Cono</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active"><a class="nav-link" href="#">홈</a></li>
      <li class="nav-item"><a class="nav-link" href="#">대시보드</a></li>
      <li class="nav-item"><a class="nav-link" href="AdminDealList.admin">거래관리</a></li>
      <li class="nav-item"><a class="nav-link" href="AdminMemberList.admin">회원관리</a></li>
      <li class="nav-item"><a class="nav-link" href="#">1:1문의</a></li>
      <li class="nav-item"><a class="nav-link" href="#">공지관리</a></li>
      <li class="nav-item"><a class="nav-link" href="#">신고관리</a></li>
    </ul>
  </div>
	<ul class="nav navbar-nav navbar-right">
		<li class="nav-item"><a class="nav-link" href="#">로그인</a></li>
		<li class="nav-item"><a class="nav-link" href="#">회원가입</a></li>
	</ul>
</header>

<div class="container-fluid">
  <div class="row flex-nowrap">
    <div class="col-3 bd-sidebar">
      <div class="bd-sidebar-body">
        <ul class="nav">
          <li><a href="#">대시보드</a></li>
	      <li><a href="AdminDealList.admin">거래관리</a></li>
	      <li><a href="AdminMemberList.admin">회원관리</a></li>
	      <li><a href="#">1:1문의</a></li>
	      <li><a href="#">공지관리</a></li>
	      <li><a href="#">신고관리</a></li>
        </ul>
        <br>
      </div>
      <div class='bd-sidebar-footer'>
        Sidebar Footer
      </div>
    </div>
    <main class="col-9 py-md-3 pl-md-5 bd-content" role="main">
      <h1>Main 1</h1>
      <h1>Main 2</h1>
      <h1>Main 3</h1>
      <h1>Main 4</h1>
    </main>
  </div>
</div>


</body>
</html>