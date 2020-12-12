<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>403 Error</title>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%= contextPath %>/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%= contextPath %>/static/css/style.css">
    <style type="text/css">
    @import url("https://fonts.googleapis.com/css?family=Bungee");

body {
  background: #1b1b1b;
  color: white;
  font-family: "Bungee", cursive;
  margin-top: 50px;
  text-align: center;
}
a {
  color: #2aa7cc;
  text-decoration: none;
}
a:hover {
  color: white;
}
svg {
  width: 50vw;
}
.lightblue {
  fill: #444;
}
.eye {
  cx: calc(115px + 30px * var(--mouse-x));
  cy: calc(50px + 30px * var(--mouse-y));
}
#eye-wrap {
  overflow: hidden;
}
.error-text {
  font-size: 120px;
}
.alarm {
  animation: alarmOn 0.5s infinite;
}

@keyframes alarmOn {
  to {
    fill: darkred;
  }
}
    
    </style>
</head>
<body>
<svg xmlns="http://www.w3.org/2000/svg" id="robot-error" viewBox="0 0 260 118.9">
            <defs>
                <clipPath id="white-clip"><circle id="white-eye" fill="#cacaca" cx="130" cy="65" r="20" /> </clipPath>
             <text id="text-s" class="error-text" y="106"> 403 </text>
            </defs>
              <path class="alarm" fill="#e62326" d="M120.9 19.6V9.1c0-5 4.1-9.1 9.1-9.1h0c5 0 9.1 4.1 9.1 9.1v10.6" />
             <use xlink:href="#text-s" x="-0.5px" y="-1px" fill="black"></use>
             <use xlink:href="#text-s" fill="#2b2b2b"></use>
            <g id="robot">
              <g id="eye-wrap">
                <use xlink:href="#white-eye"></use>
                <circle id="eyef" class="eye" clip-path="url(#white-clip)" fill="#000" stroke="#2aa7cc" stroke-width="2" stroke-miterlimit="10" cx="130" cy="65" r="11" />
<ellipse id="white-eye" fill="#2b2b2b" cx="130" cy="40" rx="18" ry="12" />
              </g>
              <circle class="lightblue" cx="105" cy="32" r="2.5" id="tornillo" />
              <use xlink:href="#tornillo" x="50"></use>
              <use xlink:href="#tornillo" x="50" y="60"></use>
              <use xlink:href="#tornillo" y="60"></use>
            </g>
          </svg>
<h1>You are not allowed to enter here</h1>
<h2>Go <a target="_blank" href="<%= contextPath %>/home">Home!</a></h2>
<script src="<%= contextPath %>/static/js/jquery.slim.min.js"></script>
    <script src="<%= contextPath %>/static/js/popper.min.js"></script>
    <script src="<%= contextPath %>/static/js/bootstrap.min.js"></script>
    <script src="<%= contextPath %>/static/js/chart.min.js"></script>
    <script src="<%= contextPath %>/static/js/custom-chart.js"></script>
<script type="text/javascript">
var root = document.documentElement;
var eyef = document.getElementById('eyef');
var cx = document.getElementById("eyef").getAttribute("cx");
var cy = document.getElementById("eyef").getAttribute("cy");

document.addEventListener("mousemove", evt => {
  let x = evt.clientX / innerWidth;
  let y = evt.clientY / innerHeight;

  root.style.setProperty("--mouse-x", x);
  root.style.setProperty("--mouse-y", y);
  
  cx = 115 + 30 * x;
  cy = 50 + 30 * y;
  eyef.setAttribute("cx", cx);
  eyef.setAttribute("cy", cy);
  
});

document.addEventListener("touchmove", touchHandler => {
  let x = touchHandler.touches[0].clientX / innerWidth;
  let y = touchHandler.touches[0].clientY / innerHeight;

  root.style.setProperty("--mouse-x", x);
  root.style.setProperty("--mouse-y", y);
});
</script>
</body>
</html>