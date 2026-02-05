package com.zee.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zee.model.Cart;
import com.zee.model.Coupon;
import com.zee.model.User;
import com.zee.repository.CartRespository;
import com.zee.repository.CouponRepository;
import com.zee.repository.UserRepository;
import com.zee.service.CouponService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponServiceImpl implements CouponService {

	private final CouponRepository couponRepository;
	private final CartRespository cartRepository;
	private final UserRepository userRepository;

	@Override
	public Cart applyCoupon(String code, double orderValue, User user) throws Exception {

		Coupon coupon = couponRepository.findByCode(code);
		Cart cart = cartRepository.findByUserId(user.getId());

		if (cart == null) {
			throw new Exception("Cart not found for user");
		}

		if (coupon == null) {
			throw new Exception("coupon not valid");
		}

		if (user.getUsedCoupons() != null && user.getUsedCoupons().contains(coupon)) {
			throw new Exception("coupon already used");
		}

		if (orderValue < coupon.getMinOrderValue()) {
			throw new Exception("order value is less then coupon min value.. " + coupon.getMinOrderValue());
		}

		boolean isDateValid = true;
		if (coupon.getValidityStartDate() != null && coupon.getValidityEndDate() != null) {
			isDateValid = LocalDate.now().isAfter(coupon.getValidityStartDate()) &&
					LocalDate.now().isBefore(coupon.getValidityEndDate());
		}

		if (coupon.isActive() && isDateValid) {

			// Temporarily removed used coupon check to fix 500 error if set is issue
			// if (user.getUsedCoupons() != null) {
			// user.getUsedCoupons().add(coupon);
			// userRepository.save(user);
			// }

			double discountedPrice = (cart.getTotalSellingPrice() * coupon.getDiscount()) / 100;

			cart.setTotalSellingPrice((int) (cart.getTotalSellingPrice() - discountedPrice));
			cart.setCouponCode(code);
			cartRepository.save(cart);
			return cart;

		}
		throw new Exception("coupon not valid");
	}

	@Override
	public Cart removeCoupon(String code, User user) throws Exception {
		Coupon coupon = couponRepository.findByCode(code);
		if (coupon == null) {
			throw new Exception("coupon not found...");
		}

		Cart cart = cartRepository.findByUserId(user.getId());

		double originalPrice = cart.getTotalSellingPrice() / (1 - (coupon.getDiscount() / 100.0));

		cart.setTotalSellingPrice((int) originalPrice);
		cart.setCouponCode(null);

		return cartRepository.save(cart);
	}

	@Override
	public Coupon findCouponById(Long id) throws Exception {

		return couponRepository.findById(id).orElseThrow(() -> new Exception("coupon not found with given id"));
	}

	@Override
	// whenever user try to invoke this method spring boot will pre authoriz user
	// role
	// it allows only admin user to access this method
	@PreAuthorize("hasRole ('ADMIN')")
	public Coupon createCoupon(Coupon coupon) {

		return couponRepository.save(coupon);
	}

	@Override
	public List<Coupon> findAllCoupons() {

		return couponRepository.findAll();
	}

	@Override
	@PreAuthorize("hasRole ('ADMIN')")
	public void deleteCoupon(Long id) throws Exception {

		findCouponById(id);
		couponRepository.deleteById(id);

	}

}
