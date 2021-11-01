<template>
  <div>
    <h4 class="lighter">
      <router-link to="/business/program" class="pink"> {{program.title}} </router-link>：
      <router-link to="/business/chapter" class="pink"> {{chapter.name}} </router-link>
    </h4>
    <hr>
    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        Add
      </button>
      &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        Refresh
      </button>
    </p>

    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Video</th>
        <th>Length (Seconds)</th>
        <th>Charge</th>
        <th>Order</th>
        <th>Operation</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="episode in episodes">
        <td>{{episode.id}}</td>
        <td>{{episode.title}}</td>
        <td>{{episode.video}}</td>
        <td>{{episode.time}}</td>
        <td>{{EPISODE_CHARGE | optionKV(episode.charge)}}</td>
        <td>{{episode.sort}}</td>
      <td>
        <div class="hidden-sm hidden-xs btn-group">
          <button v-on:click="edit(episode)" class="btn btn-xs btn-info">
            <i class="ace-icon fa fa-pencil bigger-120"></i>
          </button>
          <button v-on:click="del(episode.id)" class="btn btn-xs btn-danger">
            <i class="ace-icon fa fa-trash-o bigger-120"></i>
          </button>
        </div>
      </td>
      </tr>
      </tbody>
    </table>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Form</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">Title</label>
                <div class="col-sm-10">
                  <input v-model="episode.title" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Program ID</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{program.title}}</p>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Chapter ID</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{program.title}}</p>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Video</label>
                <div class="col-sm-10">
                  <file v-bind:id="'video-upload'"
                        v-bind:suffixs="['mp4']"
                        v-bind:after-upload="afterUpload"></file>
                  <div v-show="episode.video" class="row">
                    <div class="col-md-9">
                      <video v-bind:src="episode.video" id="video" controls="controls"></video>
                    </div>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Length (Seconds)</label>
                <div class="col-sm-10">
                  <input v-model="episode.time" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Charge</label>
                <div class="col-sm-10">
                  <select v-model="episode.charge" class="form-control">
                    <option v-for="o in EPISODE_CHARGE" v-bind:value="o.key">{{o.value}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Order</label>
                <div class="col-sm-10">
                  <input v-model="episode.sort" class="form-control">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">Save</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  import File from "../../components/file";

  export default {
    components: {Pagination, File},
    name: "episode",
    data: function() {
      return {
        episode: {},
        episodes: [],
        EPISODE_CHARGE: EPISODE_CHARGE,
        program: {},
        chapter: {},
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 5;
            let program = SessionStorage.get("program") || {};
      let chapter = SessionStorage.get("chapter") || {};
      if (Tool.isEmpty(program) || Tool.isEmpty(chapter)) {
        _this.$router.push("/welcome");
      }
      _this.program = program;
      _this.chapter = chapter;
      _this.list(1);
      // sidebar激活样式方法一
      this.$parent.activeSidebar("business-episode-sidebar");

    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.episode = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(episode) {
        let _this = this;
        _this.episode = $.extend({}, episode);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/episode/list', {
          page: page,
          size: _this.$refs.pagination.size,
          programId: _this.program.id,
          chapterId: _this.chapter.id
        }).then((response)=>{
          let resp = response.data;
          _this.episodes = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);

        })
      },

      /**
       * 点击【保存】
       */
      save(page) {
        let _this = this;

        // 保存校验
        if (1 != 1
          || !Validator.require(_this.episode.title, "Title")
          || !Validator.length(_this.episode.title, "Title", 1, 50)
          || !Validator.length(_this.episode.video, "Video", 1, 200)
        ) {
          return;
        }
        _this.episode.programId = _this.program.id;
        _this.episode.chapterId = _this.chapter.id;

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/episode/save', _this.episode).then((response)=>{
          let resp = response.data;
          if (resp.success) {
            $("#form-modal").modal("hide");
            _this.list(1);
            Toast.success("Your file has been saved!");
          } else {
            Toast.warning(resp.message)
          }
        })
      },

      /**
       * 点击【删除】
       */
      del(id) {
        let _this = this;
        Confirm.show("You won't be able to revert this!", function () {
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/episode/delete/' + id).then((response)=>{
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("Your file has been deleted!");
            }
          })
        });
      },

      afterUpload(resp) {
        let _this = this;
        let video = resp.content.path;
        _this.episode.video = video;
                _this.getTime();
      },

      /**
       * 获取时长
       */
      getTime() {
        let _this = this;
        let ele = document.getElementById("video");
        _this.episode.time = parseInt(ele.duration, 10);
      },
    }
  }
</script>

<style scoped>
  video {
    width: 100%;
    height: auto;
    margin-top: 10px;
  }
</style>