//
//  AdmobManager.h
//  PuzzleGame
//
//  Created by fx on 14/10/27.
//  Copyright (c) 2014年 FS. All rights reserved.
//
#import "BMDefine.h"
#import <Foundation/Foundation.h>

#import "GADBannerView.h"
#import "GADRequest.h"
#import <StoreKit/StoreKit.h>
#import "GADInterstitial.h"


#define KADMOBSTATECHANGE @"kAdmobStateChange"

@interface AdmobManager : NSObject<GADBannerViewDelegate,GADInterstitialDelegate>
{

}


/*!
 *  单例宏的调用
 */
AS_SINGLETON(AdmobManager)

@property(nonatomic, strong) GADBannerView *bannerView;
@property(nonatomic, strong) UIViewController *rootViewController;
@property(readwrite) BOOL bIsBannerViewReady;

@property (nonatomic, strong) GADInterstitial *adInterstitial;
@property(nonatomic,strong) UIViewController *curControlView;
@property(readwrite) BOOL bIsFirstGADInterstitial;

@property (readwrite) CGFloat floatBannerWidth;
@property (readwrite) CGFloat floatBannerHeight;

//CGFloat _floatBannerHeight;
//CGFloat _floatBannerWidth;
-(instancetype)initWithViewController:(UIViewController*)rootViewController;
-(void)startInterstitialView;
@end
