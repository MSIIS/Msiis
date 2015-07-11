package com.web.service.impl;


import com.util.model.DataType;
import com.util.model.QueryRule;
import com.web.service.BaserService;
import com.web.service.RollService;
import com.web.soupe.roll.Roll;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service("ssqServiceImpl")
@Transactional(readOnly = true)
public  class RollServiceImpl extends BaserService implements RollService {

	@Override
	@Transactional(readOnly = false)
	public List<Roll> getNumbersOfKill(String[] reds, String[] blues, int num,
			int sum1, int sum2, String sort) {
		List<Roll> rolls = new LinkedList<Roll>();
		List<Integer> redTotal = this.getTotle(33);
		List<Integer> blueTotal = this.getTotle(16);

		int jj = 1;

		if (reds != null) {
			for (String red : reds) {
				Integer i = Integer.parseInt(red);
				redTotal.remove(i);
			}
		}
		if (blues != null) {
			for (String blue : blues) {
				Integer i = Integer.parseInt(blue);
				blueTotal.remove(i);
			}
		}
		while (jj <= num) {
			List<Integer> r1 = new LinkedList<Integer>();
			List<Integer> b1 = new LinkedList<Integer>();
			Set<Integer> redIndexSet = this.getIndex(redTotal, 6);
			Set<Integer> blueIndexSet = this.getIndex(blueTotal, 1);
			for (Integer inte : blueIndexSet) {
				b1.add(blueTotal.get(inte));
			}

			int sum = 0;
			for (Integer inte : redIndexSet) {
				r1.add(redTotal.get(inte));
				sum += redTotal.get(inte);
			}
			if (sum > sum1 && sum < sum2) {
				Roll roll = new Roll();
				Collections.sort(r1);
				roll.setRed(this.convertToString(r1));
				roll.setBlue(this.convertToString(b1));
				roll.setNumberFiel(sort);
				roll.setRedSum(sum);
				roll.setDataSourceType(DataType.SSQ_FOR_SOURE_TYPE_KILL);
				rolls.add(roll);
				jj++;
			}
		}
		daoManager.getRollDaoH4().saveAll(rolls);
		return rolls;
	}

	public List<Integer> getTotle(int num) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= num; i++) {
			list.add(i);
		}
		return list;
	}

	public Set<Integer> getIndex(Collection<Integer> coll, int k) {
		Set<Integer> redIndexSet = new HashSet<Integer>();
		Random r = new Random();
		while (redIndexSet.size() < k) {
			int j = r.nextInt(coll.size());
			redIndexSet.add(j);
		}

		return redIndexSet;
	}

	public String convertToString(List<Integer> list) {
		String s = "";
		for (Integer i : list) {
			s = s + i + "  ";
		}
		return s;
	}

	@Override
	public List<Roll> findNums(String sort, String dataType) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("dataSourceType", dataType);
		queryRule.addLike("numberFiel", sort+"%");
		return daoManager.getRollDaoH4().find(queryRule);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteNums(String ids) {
		List<Long> list = new ArrayList<Long>();
		if (!StringUtils.isEmpty(ids)) {
			String[] array = ids.split(",");
			for (String id : array) {
				list.add(Long.parseLong(id));
			}
			daoManager.getRollDaoH4().deleteByPks(list);
		}

	}

    @Override
    public List<Roll> findListByJdbcSql(Long id) {

        return this.getDaoManager().getRollDaoH4().findObjectByJdbc(id);
    }

    @Override
	@Transactional(readOnly = false)
	public List<Roll> getNumbersOfChoose(String[] reds, String[] blues,
			int num, int sum1, int sum2, String sort) {
		List<Roll> rolls = new LinkedList<Roll>();
		Set<Integer> reds_a = new HashSet<Integer>();
		Set<Integer> blus_a = new HashSet<Integer>();
		int jj = 1;
		if (reds != null) {
			for (String red : reds) {
				Integer i = Integer.parseInt(red);
				reds_a.add(i);
			}
		}
		if (blues != null) {
			for (String blue : blues) {
				Integer i = Integer.parseInt(blue);
				blus_a.add(i);
			}
		}
		List<Integer> r = new LinkedList<Integer>();
		List<Integer> b = new LinkedList<Integer>();

		b.addAll(blus_a);
		r.addAll(reds_a);

		while (jj <= num) {
			List<Integer> r1 = new LinkedList<Integer>();
			List<Integer> b1 = new LinkedList<Integer>();
			Set<Integer> redIndexSet = this.getIndex(r, 6);
			Set<Integer> blueIndexSet = this.getIndex(b, 1);
			for (Integer inte : blueIndexSet) {
				b1.add(b.get(inte));
			}

			int sum = 0;
			for (Integer inte : redIndexSet) {
				r1.add(r.get(inte));
				sum += r.get(inte);
			}
			if (sum > sum1 && sum < sum2) {
				Roll roll = new Roll();
				Collections.sort(r1);
				roll.setRed(this.convertToString(r1));
				roll.setBlue(this.convertToString(b1));
				roll.setNumberFiel(sort);
				roll.setRedSum(sum);
				roll.setDataSourceType(DataType.SSQ_FOR_SOURE_TYPE_CHOOSE);
				rolls.add(roll);
				jj++;
			}
		}
		daoManager.getRollDaoH4().saveAll(rolls);
		return rolls;
	}

}
