<div id="message">
  <span id="msg-content"></span>
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
  padding: 10px;
  border-radius: 3px;
  border: solid 1px #ebccd1;
  transition: all .3s;
  z-index: 1000;
}

.msg-show {
  top: 50px !important;
  opacity: 1 !important;
}

.success {
  color: #67C23A;
  background-color: #f0f9eb;
}

.info {
  color: #31708f;
  background-color: #d9edf7;
}

.warning {
  color: #E6A23C;
  background-color: #fdf6ec;
}

.error {
  color: #f56c6c;
  background-color: #fef0f0;
}
</style>

<script>
function showMsg(type, content, delay) {
  $('#message').removeClass().addClass(type)
  $('#msg-content').html(content);
  $('#message').addClass('msg-show');
  if (delay) {
    setTimeout(() => {
      hideMsg();
    }, delay)
  }
}

function hideMsg() {
  $('#message').removeClass('msg-show');
}
</script>