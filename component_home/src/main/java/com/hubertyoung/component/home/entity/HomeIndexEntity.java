package com.hubertyoung.component.home.entity;


import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:Yang
 * @date:2018/5/9 1:06 PM
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.home.entity
 */
public class HomeIndexEntity {

	public List< NewGoodsListBean > newGoodsList;
	public List< ChannelBean > channel;
	public List< BannerBean > banner;
	public List< BrandListBean > brandList;
	public List< HotGoodsListBean > hotGoodsList;
	public List< TopicListBean > topicList;
	public List< FloorGoodsListBean > floorGoodsList;

	public static class NewGoodsListBean {
		/**
		 * id : 1116011
		 * goodsSn : 1116011
		 * name : 蔓越莓曲奇 200克
		 * categoryId : 1008015
		 * brandId : 0
		 * gallery : ["http://yanxuan.nosdn.127.net/1900349493220bfebcc67b2b6466e9f5.jpg","http://yanxuan.nosdn.127.net/1900349493220bfebcc67b2b6466e9f5.jpg","http://yanxuan
		 * .nosdn.127.net/1900349493220bfebcc67b2b6466e9f5.jpg","http://yanxuan.nosdn.127.net/1900349493220bfebcc67b2b6466e9f5.jpg","http://yanxuan
		 * .nosdn.127.net/1900349493220bfebcc67b2b6466e9f5.jpg"]
		 * keywords :
		 * goodsBrief : 酥脆奶香，甜酸回味
		 * isOnSale : true
		 * sortOrder : 5
		 * counterPrice : 0
		 * isNew : true
		 * primaryPicUrl : http://yanxuan.nosdn.127.net/767b370d07f3973500db54900bcbd2a7.png
		 * listPicUrl : http://yanxuan.nosdn.127.net/1900349493220bfebcc67b2b6466e9f5.jpg
		 * isHot : true
		 * goodsUnit : 件
		 * retailPrice : 36
		 * addTime : 2018-02-01T00:00:00
		 * deleted : false
		 */
		@SerializedName("id" )
		public int newGoodsId;
		public String goodsSn;
		public String name;
		public int categoryId;
		public int brandId;
		public String keywords;
		public String goodsBrief;
		public boolean isOnSale;
		public int sortOrder;
		public int counterPrice;
		public boolean isNew;
		public String primaryPicUrl;
		public String listPicUrl;
		public boolean isHot;
		public String goodsUnit;
		public int retailPrice;
		public String addTime;
		public boolean deleted;
		public List< String > gallery;
	}

	public static class ChannelBean {
		/**
		 * id : 1005000
		 * name : 居家
		 * iconUrl : http://yanxuan.nosdn.127.net/a45c2c262a476fea0b9fc684fed91ef5.png
		 */
		@SerializedName("id" )
		public int channelId;
		public String name;
		public String iconUrl;
	}

	public static class BannerBean {
		/**
		 * id : 4
		 * position : 1
		 * name : 111
		 * link :
		 * url : http://122.152.206.172:8081/os/storage/fetch/8ekari6nzswl216uj09v.jpeg
		 * content : 222
		 * enabled : true
		 * deleted : false
		 */
		@SerializedName("id" )
		public int bannerId;
		public int position;
		public String name;
		public String link;
		public String url;
		public String content;
		public boolean enabled;
		public boolean deleted;
	}

	public static class BrandListBean {
		/**
		 * id : 1001000
		 * name : MUJI制造商
		 * listPicUrl : http://yanxuan.nosdn.127.net/1541445967645114dd75f6b0edc4762d.png
		 * simpleDesc : 严选精选了MUJI制造商和生产原料，
		 用几乎零利润的价格，剔除品牌溢价，
		 让用户享受原品牌的品质生活。
		 * picUrl : http://yanxuan.nosdn.127.net/4ea3f1e60dd77c45c218e503d721a1ed.jpg
		 * sortOrder : 2
		 * isShow : true
		 * floorPrice : 12.9
		 * appListPicUrl : http://yanxuan.nosdn.127.net/1541445967645114dd75f6b0edc4762d.png
		 * isNew : true
		 * newPicUrl : http://yanxuan.nosdn.127.net/4ea3f1e60dd77c45c218e503d721a1ed.jpg
		 * newSortOrder : 2
		 * addTime : 2018-02-01T00:00:00
		 * deleted : false
		 */
		@SerializedName("id" )
		public int brandId;
		public String name;
		public String listPicUrl;
		public String simpleDesc;
		public String picUrl;
		public int sortOrder;
		public boolean isShow;
		public float floorPrice;
		public String appListPicUrl;
		public boolean isNew;
		public String newPicUrl;
		public int newSortOrder;
		public String addTime;
		public boolean deleted;
	}

	public static class HotGoodsListBean {
		/**
		 * id : 1006013
		 * goodsSn : 1006013
		 * name : 双宫茧桑蚕丝被 空调被
		 * categoryId : 1036000
		 * brandId : 1001045
		 * gallery : ["http://yanxuan.nosdn.127.net/d83cbd9ec177276ba2582ee393eff3db.jpg","http://yanxuan.nosdn.127.net/b73852cf22939c4995a5bc8996a4afdd.jpg","http://yanxuan
		 * .nosdn.127.net/d2fe16d259e0187d6b53eef028e843d1.jpg","http://yanxuan.nosdn.127.net/4e8f5c09ae9dd03b5ae5b1287b598cc5.jpg"]
		 * keywords :
		 * goodsBrief : 一级桑蚕丝，吸湿透气柔软
		 * isOnSale : true
		 * sortOrder : 7
		 * counterPrice : 0
		 * isNew : false
		 * primaryPicUrl : http://yanxuan.nosdn.127.net/583812520c68ca7995b6fac4c67ae2c7.png
		 * listPicUrl : http://yanxuan.nosdn.127.net/7b95e6b91133f8d8fd56505c47d2fa29.jpg
		 * isHot : true
		 * goodsUnit : 件
		 * retailPrice : 699
		 * addTime : 2018-02-01T00:00:00
		 * deleted : false
		 */
		@SerializedName("id" )
		public int hotGoodsId;
		public String goodsSn;
		public String name;
		public int categoryId;
		public int brandId;
		public String keywords;
		public String goodsBrief;
		public boolean isOnSale;
		public int sortOrder;
		public int counterPrice;
		public boolean isNew;
		public String primaryPicUrl;
		public String listPicUrl;
		public boolean isHot;
		public String goodsUnit;
		public int retailPrice;
		public String addTime;
		public boolean deleted;
		public List< String > gallery;
	}

	public static class TopicListBean {
		/**
		 * id : 264
		 * title : 设计师们推荐的应季好物
		 * avatar : https://yanxuan.nosdn.127.net/14920662001560500.png
		 * itemPicUrl : https://yanxuan.nosdn.127.net/14918201901050274.jpg
		 * subtitle : 原创设计春款系列上新
		 * priceInfo : 29.9
		 * readCount : 77.7k
		 * scenePicUrl : https://yanxuan.nosdn.127.net/14918201901050274.jpg
		 * sortOrder : 0
		 * isShow : true
		 * addTime : 2018-02-01T00:00:00
		 * deleted : false
		 * content : <p><img src="//yanxuan.nosdn.127.net/75c55a13fde5eb2bc2dd6813b4c565cc.jpg" data-mce-src="//yanxuan.nosdn.127.net/75c55a13fde5eb2bc2dd6813b4c565cc.jpg"> <img src="//yanxuan
		 * .nosdn.127.net/e27e1de2b271a28a21c10213b9df7e95.jpg" data-mce-src="//yanxuan.nosdn.127.net/e27e1de2b271a28a21c10213b9df7e95.jpg"> <img src="//yanxuan
		 * .nosdn.127.net/9d413d1d28f753cb19096b533d53418d.jpg" data-mce-src="//yanxuan.nosdn.127.net/9d413d1d28f753cb19096b533d53418d.jpg"> <img src="//yanxuan
		 * .nosdn.127.net/64b0f2f350969e9818a3b6c43c217325.jpg" data-mce-src="//yanxuan.nosdn.127.net/64b0f2f350969e9818a3b6c43c217325.jpg"> <img src="//yanxuan
		 * .nosdn.127.net/a668e6ae7f1fa45565c1eac221787570.jpg" data-mce-src="//yanxuan.nosdn.127.net/a668e6ae7f1fa45565c1eac221787570.jpg"> <img src="//yanxuan
		 * .nosdn.127.net/0d4004e19728f2707f08f4be79bbc774.jpg" data-mce-src="//yanxuan.nosdn.127.net/0d4004e19728f2707f08f4be79bbc774.jpg"> <img src="//yanxuan
		 * .nosdn.127.net/79ee021bbe97de7ecda691de6787241f.jpg" data-mce-src="//yanxuan.nosdn.127.net/79ee021bbe97de7ecda691de6787241f.jpg"></p>
		 */
		@SerializedName("id" )
		public int topicId;
		public String title;
		public String avatar;
		public String itemPicUrl;
		public String subtitle;
		public double priceInfo;
		public String readCount;
		public String scenePicUrl;
		public int sortOrder;
		public boolean isShow;
		public String addTime;
		public boolean deleted;
		public String content;
	}

	public static class FloorGoodsListBean {
		/**
		 * name : 居家
		 * goodsList : [{"id":1009024,"goodsSn":"1009024","name":"日式和风懒人沙发","categoryId":1008002,"brandId":0,"gallery":["http://yanxuan.nosdn.127.net/9460f6b30661548c4a864607bfcdf4ca.jpg",
		 * "http://yanxuan.nosdn.127.net/acbdb480bcad193fad77ef6c4f52192e.jpg","http://yanxuan.nosdn.127.net/e6feb5f4a0989d212bce068d4907657d.jpg","http://yanxuan
		 * .nosdn.127.net/6059ab6e106d97c29d5723c1d6f1a11f.jpg"],"keywords":"","goodsBrief":"优质莱卡纯棉，和风家居新体验","isOnSale":true,"sortOrder":1,"counterPrice":0,"isNew":false,
		 * "primaryPicUrl":"http://yanxuan.nosdn.127.net/149dfa87a7324e184c5526ead81de9ad.png","listPicUrl":"http://yanxuan.nosdn.127.net/bcaf7ee314af7dbfb04612087e563249.jpg","isHot":false,
		 * "goodsUnit":"件","retailPrice":599,"addTime":"2018-02-01T00:00:00","deleted":false},{"id":1015007,"goodsSn":"1015007","name":"典雅美式全棉刺绣抱枕","categoryId":1008002,"brandId":0,
		 * "gallery":["http://yanxuan.nosdn.127.net/013657a5a5faf8a9a7e3f39b5bba4eac.jpg","http://yanxuan.nosdn.127.net/d46ba997e163430e43735e4ad1caeff0.jpg","http://yanxuan
		 * .nosdn.127.net/a90e545295d22de031b10aee631e48fe.jpg","http://yanxuan.nosdn.127.net/f7188ec871d1f721f64cbe04860a4fe2.jpg"],"keywords":"","goodsBrief":"典雅毛线绣，精致工艺","isOnSale":true,
		 * "sortOrder":4,"counterPrice":0,"isNew":false,"primaryPicUrl":"http://yanxuan.nosdn.127.net/a2045004de8a6225289376ad54317fc8.png","listPicUrl":"http://yanxuan
		 * .nosdn.127.net/d16d6fb25f3d6d8c356fcd8e178bdd26.jpg","isHot":false,"goodsUnit":"件","retailPrice":59,"addTime":"2018-02-01T00:00:00","deleted":false},{"id":1020000,"goodsSn":"1020000",
		 * "name":"升级款记忆绵护椎腰靠","categoryId":1008002,"brandId":1001000,"gallery":["http://yanxuan.nosdn.127.net/e163b42594b58936ee8500abb8b4112c.jpg","http://yanxuan
		 * .nosdn.127.net/1f6f41a8c5cdafe375548d77e9f06d78.jpg","http://yanxuan.nosdn.127.net/b69fd91ecc1c9b9aa431b8df4298a6a1.jpg","http://yanxuan.nosdn.127.net/a621c700d49357a4ab46c6c7a97fa83c
		 * .jpg"],"keywords":"","goodsBrief":"人体工学设计，缓解腰背疼痛","isOnSale":true,"sortOrder":15,"counterPrice":0,"isNew":false,"primaryPicUrl":"http://yanxuan
		 * .nosdn.127.net/819fdf1f635a694166bcfdd426416e8c.png","listPicUrl":"http://yanxuan.nosdn.127.net/84563d90b0c10c4d4a8229fd34cb4063.jpg","isHot":false,"goodsUnit":"件","retailPrice":79,
		 * "addTime":"2018-02-01T00:00:00","deleted":false},{"id":1030001,"goodsSn":"1030001","name":"160*230羊毛手工地毯","categoryId":1008002,"brandId":0,"gallery":["http://yanxuan
		 * .nosdn.127.net/b57e971ab0de96e159c2e8de13df25bd.jpg","http://yanxuan.nosdn.127.net/1e06cd5c6107e37214ea9cf13ef08676.jpg","http://yanxuan
		 * .nosdn.127.net/a7351368b2e1d57958c66a7225230b24.jpg","http://yanxuan.nosdn.127.net/3b9d726451cbe3c2d4432613d48bc6e9.jpg"],"keywords":"","goodsBrief":"印度进口，手工编织，简约百搭","isOnSale":true,
		 * "sortOrder":25,"counterPrice":0,"isNew":false,"primaryPicUrl":"http://yanxuan.nosdn.127.net/88dc5d80c6f84102f003ecd69c86e1cf.png","listPicUrl":"http://yanxuan
		 * .nosdn.127.net/ca0d9199db70d7b7f2b9b2ea673c74a4.jpg","isHot":false,"goodsUnit":"件","retailPrice":969,"addTime":"2018-02-01T00:00:00","deleted":false},{"id":1030002,"goodsSn":"1030002",
		 * "name":"160*230羊毛圈绒枪刺地毯","categoryId":1008002,"brandId":0,"gallery":["http://yanxuan.nosdn.127.net/63096efbd6271a42d3d830e79bf9635f.jpg","http://yanxuan
		 * .nosdn.127.net/50643ebbcf8a243ca609477f431fe75a.jpg","http://yanxuan.nosdn.127.net/bfc9ea77fa117eaa6be19ca7329d4c95.jpg","http://yanxuan
		 * .nosdn.127.net/a8540865c48fb297f77d30cdf3fb4884.jpg"],"keywords":"","goodsBrief":"印度进口，手工枪刺，简约百搭","isOnSale":true,"sortOrder":24,"counterPrice":1269,"isNew":false,
		 * "primaryPicUrl":"http://yanxuan.nosdn.127.net/8b9328496990357033d4259fda250679.png","listPicUrl":"http://yanxuan.nosdn.127.net/0d0a7e7d40a16ae6850d19f5e8704d8e.jpg","isHot":false,
		 * "goodsUnit":"件","retailPrice":899,"addTime":"2018-02-01T00:00:00","deleted":false}]
		 * id : 1005000
		 */

		public String name;
		@SerializedName("id" )
		public int floorGoodsid;
		public List< GoodsListBean > goodsList;

		public static class GoodsListBean {
			/**
			 * id : 1009024
			 * goodsSn : 1009024
			 * name : 日式和风懒人沙发
			 * categoryId : 1008002
			 * brandId : 0
			 * gallery : ["http://yanxuan.nosdn.127.net/9460f6b30661548c4a864607bfcdf4ca.jpg","http://yanxuan.nosdn.127.net/acbdb480bcad193fad77ef6c4f52192e.jpg","http://yanxuan
			 * .nosdn.127.net/e6feb5f4a0989d212bce068d4907657d.jpg","http://yanxuan.nosdn.127.net/6059ab6e106d97c29d5723c1d6f1a11f.jpg"]
			 * keywords :
			 * goodsBrief : 优质莱卡纯棉，和风家居新体验
			 * isOnSale : true
			 * sortOrder : 1
			 * counterPrice : 0
			 * isNew : false
			 * primaryPicUrl : http://yanxuan.nosdn.127.net/149dfa87a7324e184c5526ead81de9ad.png
			 * listPicUrl : http://yanxuan.nosdn.127.net/bcaf7ee314af7dbfb04612087e563249.jpg
			 * isHot : false
			 * goodsUnit : 件
			 * retailPrice : 599
			 * addTime : 2018-02-01T00:00:00
			 * deleted : false
			 */
			@SerializedName("id" )
			public int goodsId;
			public String goodsSn;
			public String name;
			public int categoryId;
			public int brandId;
			public String keywords;
			public String goodsBrief;
			public boolean isOnSale;
			public long sortOrder;
			public double counterPrice;
			public boolean isNew;
			public String primaryPicUrl;
			public String listPicUrl;
			public boolean isHot;
			public String goodsUnit;
			public double retailPrice;
			public String addTime;
			public boolean deleted;
			public List< String > gallery;
		}
	}
}
