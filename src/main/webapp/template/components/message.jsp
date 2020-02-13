<div v-show="show" class="message" v-bind:class="type">
  <span></span>
  <i class="fa fa-times" v-on:click="close"></i>
</div>

<style>
.message {
  position: fixed;
  top: 1rem;
  left: 50%;
  transform: translateX(-50%);
  min-width: 380px;
  font-size: 0.7rem;
  padding: 0.6rem;
  border-radius: 3px;
  border: solid 1px #ebccd1;
  transition: all .3s;
  z-index: 1001;
}

.message span {
  margin-left: 5px;
}

.success {
  color: #67C23A;
  background-color: #f0f9eb;
}

.info {
  color: #31708f;
  background-color:#d9edf7;
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

.message-fade-enter,
.message-fade-leave-to {
  opacity: 0;
  top: 0px;
}
</style>