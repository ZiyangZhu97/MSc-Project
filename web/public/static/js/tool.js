Tool = {
  /**
   * 空校验 null或""都返回true
   */
  isEmpty: function (obj) {
    if ((typeof obj == 'string')) {
      return !obj || obj.replace(/\s+/g, "") == ""
    } else {
      return (!obj || JSON.stringify(obj) === "{}" || obj.length === 0);
    }
  },

  /**
   * 非空校验
   */
  isNotEmpty: function (obj) {
    return !this.isEmpty(obj);
  },

  /**
   * 长度校验
   */
  isLength: function (str, min, max) {
    return $.trim(str).length >= min && $.trim(str).length <= max;

  },

  /**
   * 保存登录用户信息
   */
  setLoginMember: function (loginMember) {
    SessionStorage.set(SESSION_KEY_LOGIN_MEMBER, loginMember);
  },

  /**
   * 获取登录用户信息
   */
  getLoginMember: function () {
    return SessionStorage.get(SESSION_KEY_LOGIN_MEMBER) || {};
  },



 /**
  * 对象数组按key对应的值排序
  * @param array
  * @param key
  * @returns {*}
  */
     sortAsc: function (array, key) {
        return array.sort(function (obj1, obj2) {
          let val1 = obj1[key];
          let val2 = obj2[key];
          console.log(val1, val2);
          if (val1 < val2) {
            return -1;
          } else if (val1 > val2) {
            return 1;
          } else {
            return 0;
          }
        });
    }

};