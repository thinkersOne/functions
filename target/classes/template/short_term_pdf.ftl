<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"></meta>
        <title>
            外请车临时协议 - 副本
        </title>
		<style>
            @page {
                size: 400mm 200mm;
                /*font-family: SimSun;*/
                /*font-size: 30px;*/
                @bottom-center{
                    font-family: SimSun;
                    font-size: 150px;
                };
            }
            .title{
                text-align: center;
            }
            table {
                background-color: transparent;
            }
            table,tr,th,td{
                border: 1px solid #dfe6ec;
                border-collapse:collapse;
                padding: 5px;
            }
            /**
			下划线
			 */
            .underline{
                text-decoration:underline;
            }
            /**
			缩进
			 */
            .textIndent{
                text-indent: 1cm;
            }
            /**
            一排
             */
            .purchaserSeal{
                margin-left: 2%;
            }
            .sellerSignature{
                margin-left: 50%;
            }
            .sellerSignature1{
                margin-left: 60%;
            }
        </style>
    </head>
	<body style="font-family: SimSun">
		<div class="title"><h3>临时合作协议</h3></div>
		<div>
			<p>甲方:<span class="underline">${purchaserCompanyName}</span></p>
			<p>乙方:<span class="underline">${sellerCompanyName}</span></p>
			<p>身份号码:<span class="underline">${idCardName}</span></p>
			<p>车牌号:<span class="underline">${vehicleLicense}</span></p>
			<p>甲乙双方根据《中华人民共和国合同法》等国家现行法律法规及其他规范性文件之规定，在平等自愿的基础上，就乙方为甲方提供临时货物运输服务，签署本协议，以期共同遵守：</p>
			<p>1.合作线路及运费:</p>
			<table>
				<tr>
					<td>线路</td>
					<td>车型</td>
					<td>点位数量</td>
					<td>公里数</td>
					<td>时效</td>
					<td>结算价</td>
				</tr>
				<#list lineAmountDTOList as lineAmountDTO>
                    <tr>
                        <td>${lineAmountDTO.lineName}</td>
                        <td>${lineAmountDTO.vehicleTypeName}</td>
                        <td>${lineAmountDTO.portCount}</td>
                        <td>${lineAmountDTO.settleDistance}</td>
                        <td>${lineAmountDTO.runtimeMinute}</td>
                        <td>${lineAmountDTO.amount}</td>
                     </tr>
                </#list>
			</table>
			<p>2.合作期限</p>
			<p>自 <span class="underline">${startYear}</span> 年 <span class="underline">${startMonth}</span> 月 <span class="underline">${startDay}</span> 日起至  <span class="underline">${endYear}</span> 年 <span class="underline">${endMonth}</span> 月 <span class="underline">${endDay}</span> 日止。</p>
			<p>3.押金 ${deposits} 元，货物安全完整送达后甲方连同运费一起支付乙方。</p>
			<p>4.运费</p>
			<p class="textIndent">4.1合作结束后15日内，甲乙双方对账。甲方在收到乙方的增值税专用发票（税点10%）后支付乙方运费。</p>
			<p class="textIndent">4.2乙方收款人姓名 <span class="underline">${sellerCompanyName}</span>；开户行名称 <span class="underline">${sellerBankName}</span> ；账户 <span class="underline">${sellerBankAccount}</span></p>
			<p>5.乙方义务与责任</p>
			<p class="textIndent">5.1乙方运输相关资质问题导致的罚款由乙方承担，给甲方造成损失的，应赔偿甲方的全部损失；</p>
			<p class="textIndent">5.2乙方应遵守甲方及甲方客户的所有管理安排、考核制度等。甲方客户对甲方的罚款由乙方承担，甲方在运费中扣除。</p>
			<p class="textIndent">5.3未经甲方同意禁止乙方在途更换车辆，否则，所有损失由乙方承担，给甲方造成损失的，还应赔偿甲方的全部损失；因不可抗力导致的车辆途中维修、事故、堵车、恶劣天气等，需向甲方及时报备影响时间；</p>
			<p class="textIndent">5.4.乙方应提供安全合法的车辆以及司乘人员并按法律要求缴纳人员/车辆保险和货物险，道路运输过程中发生交通事故以及其他事故的，由乙方承担全部责任，与甲方无关；同时，因乙方责任导致现场处理的，乙方承担甲方往返差旅费用；</p>
			<p class="textIndent">5.5甲方交货后货物毁损灭失的风险由乙方承担；乙方伪造或变造客户签收回单的，乙方应赔偿全部损失。</p>
			<p class="textIndent">5.6任何情况下禁止乙方扣留甲方托运货物，否则，甲方有权解除合同，要求乙方赔偿全部损失。</p>
			<p class="textIndent">5.7乙方违约的，甲方还有权不予支付剩余尾款，要求乙方承担人民币10万元的违约金。</p>
			<p class="textIndent">5.8甲方的全部损失包括但不限于甲方客户对甲方的罚款、对第三方的赔偿、差旅费、诉讼费、律师费、调查费、鉴定费、保全费等。</p>
			<p>6.通知送达：</p>
			<p class="textIndent">甲方联系地址：<span class="underline">${purchaserCompanyName}</span>  联系电话：<span class="underline">${purchaserPhone}</span></p>
			<p class="textIndent">乙方联系地址： <span class="underline">${sellerCompanyName}</span> 联系电话：<span class="underline">${sellerPhone}</span></p>
			<p>7.本协议履行过程中发生的争议，协商解决，协商不成，提交甲方所在地人民法院解决。</p>
			<p>8.乙方需提供有效的车辆行驶证、营运证、身份证、驾驶证复印件。</p>
			<p>9.本协议经甲方盖章和乙方签字后立即生效，一式两份，各执一份，具有同等法律效力。</p>
			<p>（以下无正文，为盖章签署页）</p>
            <div class="textIndent">
                <div>
                    <span class="purchaserSeal">甲方（盖章）：</span>
                    <span class="sellerSignature">乙方（签字）：</span>
                </div>
                <div class="sellerSignature1">
                    <p>身份号码: <span class="underline">420684122222222</span></p>
                    <p>签署时间: <span class="underline">20200529</span></p>
                </div>
            </div>
		</div>
    </body>
</html>