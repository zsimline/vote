<div id="message">
  <span id="msg-content"></span>
  <i class="fa fa-times" onclick="hideMsg()"></i>
</div>

<style>
#message {
  position: fixed;
  top: 0px;
  left: 50%;
  transform: translateX(-50%);
  opacity: 0;
  min-width: 80%;
  font-size: 12px;
  font-weight: 600;
  padding: 10px;
  border-radius: 3px;
  transition: all .3s;
  z-index: 1000;
}
@media (min-width: 768px) {
  #message {
    min-width: 380px;
  }
}

.msg-show {
  top: 50px !important;
  opacity: 1 !important;
}

.success {
  color: #67c23a;
  background-color: #f0f9eb;
  border: solid 1px #66ff66;
}

.info {
  color: #31708f;
  background-color: #d9edf7;
  border: solid 1px #00cccc;
}

.warning {
  color: #e6a23c;
  background-color: #fdf6ec;
  border: solid 1px #cccc66;
}

.error {
  color: #f56c6c;
  background-color: #fef0f0;
  border: solid 1px #ebccd1;
}

#message .fa-times {
  color: #CCC;
  cursor: pointer;
  float: right;
  line-height: 20px;
}
</style>

<script>
function showMsg(type, content, delay=1500) {
  $('#message').removeClass().addClass(type)
  $('#msg-content').html(content);
  $('#message').addClass('msg-show');
  if (delay === -1)  return ;
  setTimeout(() => {
    hideMsg();
  }, delay)
}

function hideMsg() {
  $('#message').removeClass('msg-show');
}
</script>