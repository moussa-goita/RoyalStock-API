import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BonSortieDetailPage } from './bon-sortie-detail.page';

describe('BonSortieDetailPage', () => {
  let component: BonSortieDetailPage;
  let fixture: ComponentFixture<BonSortieDetailPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(BonSortieDetailPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
