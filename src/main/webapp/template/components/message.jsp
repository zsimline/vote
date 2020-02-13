<div id="message">
  <span id="msg-content"></span>
  <i class="fa fa-times"></i>
</div>

<style>
#message {
  position: fixed;
  top: 0px;
  left: 50%;
  transform: translateX(-50%);
  opacity: 0;
  min-width: 380px;
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

.fa-times {
  color: #CCC;
  cursor: pointer;
  float: right;
}
</style>

<script>
  function showMsg(type, content) {
    $('#message').removeClass().addClass(type)
    $('#msg-content').html(content);
    $('#message').addClass('msg-show');
  }

  function hideMsg() {
    $('#message').removeClass('msg-show');
  }
</script>