//
//  BaseViewController.h
//  TestBannerOC
//
//  Created by fx on 14/10/27.
//  Copyright (c) 2014年 FS. All rights reserved.
//
#import <Cordova/CDVViewController.h>
#import <Cordova/CDVCommandDelegateImpl.h>
#import <Cordova/CDVCommandQueue.h>
#import <UIKit/UIKit.h>

@class GADBannerView;
@interface BaseViewController : CDVViewController

@property(nonatomic, strong) GADBannerView *bannerView;

/*!
 *  广告动画
 */
-(void)startAnimateBannerView;

/*!
 *  插屏广告
 */
-(void)startInterstitialView;


/*!
 *  右边按钮
 *
 *  @param strImageName
 */
-(void)addNavRightButton:(NSString*)strImageName;

/*!
 *  左边按钮
 *
 *  @param strImageName
 */
-(void)addNavLeftButton:(NSString*)strImageName;

-(void)addBannerViewAtBottom;
@end
